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
import java.util.Locale;
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

    @GetMapping(value = "/username")
    public UserVo selectUserByUsername(@RequestParam("username") String username) {
        return userService.selectUserVoByUsername(username);
    }

    @PostMapping("/permission")
    boolean hasPermission(@RequestBody PermissionDecideDto permissionDecideDto) {
        return userService.isHasPermission(permissionDecideDto);
    }

}
