package com.dragon.resource.client;

import com.dragon.resource.client.config.AuthenticationClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenxiaolong
 */
@FeignClient(name = AuthenticationClientConfig.SERVICE_NAME, path = AuthenticationClientConfig.SERVICE_NAME + AuthenticationClientConfig.AUTHENTICATION_CLIENT_PATH)
public interface AuthenticationClient {

    /**
     * 判断用户是否有权限接口
     *
     * @param authentication
     * @param url
     * @param method
     * @return
     */
    @PostMapping(value = "/auth/permission")
    boolean decide(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);

}
