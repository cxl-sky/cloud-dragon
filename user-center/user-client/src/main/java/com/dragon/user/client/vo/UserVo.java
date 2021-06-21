package com.dragon.user.client.vo;

import com.dragon.user.client.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月21日 下午9:38
 */
@Data
public class UserVo {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private List<Role> roles;
}
