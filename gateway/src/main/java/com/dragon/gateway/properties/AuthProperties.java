package com.dragon.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月16日 上午10:38
 */
@Data
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private Boolean enabled;
    private List<String> ignoreUrls;
}
