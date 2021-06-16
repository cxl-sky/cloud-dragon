package com.dragon.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月16日 下午5:13
 */
@Data
public class DragonUser {

    private String id;
    private String username;
    private Set<String> authorities;
    private String author;
    private String remark;
}
