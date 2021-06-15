package com.dragon.resource.service.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenxiaolong
 */
public interface AuthenticationService {

    /**
     * 校验权限
     *
     * @param authRequest
     * @return 是否有权限
     */
    boolean decide(HttpServletRequest authRequest);

}
