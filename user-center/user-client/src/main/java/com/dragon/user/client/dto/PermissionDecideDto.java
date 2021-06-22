package com.dragon.user.client.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月22日 上午10:32
 */
@Data
public class PermissionDecideDto {

    private String url;
    private String method;
    private List<String> roles;
}
