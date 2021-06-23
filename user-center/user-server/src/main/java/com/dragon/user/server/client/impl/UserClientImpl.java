package com.dragon.user.server.client.impl;

import com.dragon.user.client.config.UserClientConfig;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.vo.UserVo;
import com.dragon.user.server.service.MenuService;
import com.dragon.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/username")
    public UserVo selectUserByUsername(@RequestParam("username") String username) {
        return userService.selectUserVoByUsername(username);
    }

    @PostMapping("/permission")
    boolean hasPermission(@RequestBody PermissionDecideDto permissionDecideDto) {
        return userService.isHasPermission(permissionDecideDto);
    }

}
