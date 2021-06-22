package com.dragon.gateway.filters;

import com.alibaba.fastjson.JSONObject;
import com.dragon.constants.SystemConstant;
import com.dragon.exception.SystemErrorType;
import com.dragon.gateway.properties.AuthProperties;
import com.dragon.resource.client.AuthenticationClient;
import com.dragon.response.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 请求url权限校验
 * @author chenxiaolong
 */
@Configuration
@ComponentScan(basePackages = "com.dragon.resource.client")
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();


    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private AuthProperties authProperties;


    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        // 未开启验证则全部放行
        if (!authProperties.getEnabled()) {
            return chain.filter(exchange);
        }

        // 无需鉴权的放行
        boolean ignoreAuth = authProperties.getIgnoreUrls().stream().anyMatch(ignoreUrl -> antPathMatcher.match(ignoreUrl, url));
        if (ignoreAuth) {
            return chain.filter(exchange);
        }

        // 调用签权服务看用户是否有权限，若有权限进入下一个filter
        String userInfo = authenticationClient.decide(authentication, url, method);
        if (StringUtils.isNotBlank(userInfo)) {
            // 以下设置权限，必须携带token
            if (StringUtils.isEmpty(authentication)) {
                return unauthorized(exchange);
            }
            ServerHttpRequest.Builder builder = request.mutate();
            // 将用户信息传给服务
            builder.header(SystemConstant.X_CLIENT_TOKEN_USER, userInfo);
            builder.header(SystemConstant.X_CLIENT_TOKEN_ORIGIN, authentication);
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBufferFactory bufferFactory = response.bufferFactory();

        return response.writeWith(Mono.fromSupplier(() -> {
            try {
                //返回json异常原因给前端
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(Result.fail(SystemErrorType.ACCESS_DENIED)));
            } catch (JsonProcessingException e) {
                log.warn("Error writing response", e);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}
