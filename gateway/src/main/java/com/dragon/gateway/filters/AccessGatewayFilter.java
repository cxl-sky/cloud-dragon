package com.dragon.gateway.filters;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSONObject;
import com.dragon.cmn.constants.SystemConstant;
import com.dragon.gateway.properties.AuthProperties;
import com.dragon.resource.client.AuthenticationClient;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
 * @author chenxiaolong
 */
@Configuration
@ComponentScan(basePackages = "com.dragon.resource.client")
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String BEARER = "Bearer ";

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
        boolean decide = authenticationClient.decide(authentication, url, method);
        if (decide) {
            // 以下设置权限，必须携带token
            if (StringUtils.isEmpty(authentication)) {
                return unauthorized(exchange);
            }
            ServerHttpRequest.Builder builder = request.mutate();
            // 将jwt token中的用户信息传给服务
            builder.header(X_CLIENT_TOKEN_USER, getUserToken(authentication));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }

    /**
     * 提取jwt token中的数据，转为json
     *
     * @param jwtToken
     * @return
     */
    private String getUserToken(String jwtToken) {
        if (jwtToken.startsWith(BEARER)) {
            jwtToken = StringUtils.substring(jwtToken, BEARER.length());
        }
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(SystemConstant.SIGNING_KEY.getBytes())
                .parseClaimsJws(jwtToken);
        return JSONObject.toJSONString(claimsJws);
    }

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
