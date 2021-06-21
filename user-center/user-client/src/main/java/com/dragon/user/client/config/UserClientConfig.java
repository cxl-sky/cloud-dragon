package com.dragon.user.client.config;

import lombok.Data;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月13日 14:32
 */
@Data
public class UserClientConfig {

    public static final String SERVICE_NAME = "user-server";
    public static final String SERVICE_URI_PREFIX = "/user-server";

    public static final String CLIENT_PATH = "/client/users";
}
