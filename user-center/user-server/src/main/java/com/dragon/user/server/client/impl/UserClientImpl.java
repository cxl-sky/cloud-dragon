package com.dragon.user.server.client.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dragon.user.client.config.UserClientConfig;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.entity.*;
import com.dragon.user.client.vo.UserVo;
import com.dragon.user.server.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private ApiService apiService;

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

    @PostMapping("/permission")
    boolean hasPermission(@RequestBody PermissionDecideDto permissionDecideDto) {
        List<String> roles = permissionDecideDto.getRoles();
        List<Role> roleList = roleService.list(new LambdaQueryWrapper<Role>().in(Role::getRoleName, roles));
        if (CollectionUtils.isEmpty(roleList)) {
            return false;
        }
        List<RoleMenu> roleMenuList = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>()
                .in(RoleMenu::getRoleId, roleList.stream().map(Role::getRoleId).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return false;
        }
        List<Menu> menuList = menuService.list(new LambdaQueryWrapper<Menu>()
                .in(Menu::getMenuId, roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(menuList)) {
            return false;
        }
        List<String> permList = new ArrayList<>();
        for (Menu menu : menuList) {
            String[] split = menu.getPerms().split(",");
            List<String> perms = Arrays.stream(split).collect(Collectors.toList());
            permList.addAll(perms);
        }
        String url = permissionDecideDto.getUrl().replaceFirst("/", "");
        String serviceName = url.substring(0, url.indexOf("/"));
        String realUrl = url.replaceFirst(serviceName, "");
        for (String perm : permList) {
            List<Api> apis = apiService.list(new LambdaQueryWrapper<Api>().eq(Api::getService, serviceName).eq(Api::getUrl, realUrl).like(Api::getPerms, perm));
            if (!CollectionUtils.isEmpty(apis)) {
                return true;
            }
        }
        return false;
    }

    private UserVo buildUserVo(User user, List<Role> roles) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRoles(roles);
        return userVo;
    }
}
