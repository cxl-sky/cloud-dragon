package com.dragon.resource.client.entity;

import lombok.Data;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月13日 14:21
 */
@Data
public class Resource {


    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;
}
