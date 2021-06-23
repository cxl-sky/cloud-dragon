package com.dragon.pojo;

import lombok.Data;

import java.util.Set;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月16日 下午5:13
 */
@Data
public class DragonUser {

    private Long id;
    private String username;
    private Set<String> authorities;
    private String author;
    private String remark;
}
