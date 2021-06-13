package com.dragon.resource.client.config;

import lombok.Data;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月13日 14:32
 */
@Data
public class AuthenticationClientConfig {

    public static final String SERVICE_NAME = "resource-server";
    public static final String SERVICE_URI_PREFIX = "/resource-server";

    public static final String AUTHENTICATION_CLIENT_PATH = "/client/authentications";
}
