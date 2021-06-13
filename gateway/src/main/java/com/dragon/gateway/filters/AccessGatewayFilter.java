package com.dragon.gateway.filters;

import com.dragon.resource.client.AuthenticationClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 请求url权限校验
 */
@Configuration
@ComponentScan(basePackages = "com.dragon.resource.client")
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String X_CLIENT_TOKEN = "x-client-token";

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 由 resource-client模块提供签权的 feign 客户端
     */
    @Autowired
    private AuthenticationClient authenticationClient;

//    @Autowired
//    private IPermissionService permissionService;

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
        if (antPathMatcher.match("/auth-server/oauth/token", url)) {
            return chain.filter(exchange);

        }
//        不需要网关签权的url
//        if (authService.ignoreAuthentication(url)) {
//            return chain.filter(exchange);
//        }

        //调用签权服务看用户是否有权限，若有权限进入下一个filter
        boolean decide = authenticationClient.decide(authentication, url, method);
        if (decide) {
            ServerHttpRequest.Builder builder = request.mutate();
            //TODO 转发的请求都加上服务间认证token
            builder.header(X_CLIENT_TOKEN, "TODO zhoutaoo添加服务间简单认证");
            //将jwt token中的用户信息传给服务
//            builder.header(X_CLIENT_TOKEN_USER, getUserToken(authentication));
            builder.header(X_CLIENT_TOKEN_USER, "asdfasdfasdf");
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }

//    /**
//     * 提取jwt token中的数据，转为json
//     *
//     * @param authentication
//     * @return
//     */
//    private String getUserToken(String authentication) {
//        String token = "{}";
//        try {
//            token = new ObjectMapper().writeValueAsString(authService.getJwt(authentication).getBody());
//            return token;
//        } catch (JsonProcessingException e) {
//            log.error("token json error:{}", e.getMessage());
//        }
//        return token;
//    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
