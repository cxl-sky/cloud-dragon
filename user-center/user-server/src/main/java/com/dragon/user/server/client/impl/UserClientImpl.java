package com.dragon.user.server.client.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dragon.user.client.config.UserClientConfig;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.entity.User;
import com.dragon.user.client.entity.UserRole;
import com.dragon.user.client.vo.UserVo;
import com.dragon.user.server.service.RoleService;
import com.dragon.user.server.service.UserRoleService;
import com.dragon.user.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月13日 14:43
 */
@RestController
@RequestMapping(UserClientConfig.CLIENT_PATH)
public class UserClientImpl {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(value = "/username")
    public UserVo selectUserByUsername(@RequestParam("username") String username) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            return null;
        }
        List<UserRole> userRoles = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
        List<Role> roles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoles)) {
            roles = roleService.list(new LambdaQueryWrapper<Role>().in(Role::getRoleId, userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet())));
        }
        return buildUserVo(user, roles);
    }

    private UserVo buildUserVo(User user, List<Role> roles) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRoles(roles);
        return userVo;
    }
}
