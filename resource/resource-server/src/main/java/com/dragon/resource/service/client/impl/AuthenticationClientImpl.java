package com.dragon.resource.service.client.impl;

import com.dragon.resource.client.config.AuthenticationClientConfig;
import com.dragon.resource.service.pojo.HttpServletRequestAuthWrapper;
import com.dragon.resource.service.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月13日 14:43
 */
@RestController
@RequestMapping(AuthenticationClientConfig.AUTHENTICATION_CLIENT_PATH)
public class AuthenticationClientImpl {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/auth/permission")
    public String decide(@RequestParam("url") String url, @RequestParam("method") String method, HttpServletRequest request) {
        return authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
    }
}
