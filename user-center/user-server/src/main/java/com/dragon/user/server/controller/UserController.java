package com.dragon.user.server.controller;

import com.dragon.base.BaseController;
import com.dragon.pojo.DragonUser;
import com.dragon.response.Result;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.server.service.UserService;
import com.dragon.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月19日 上午9:09
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Result<DragonUser> getCurrentUserInfo() {
        DragonUser user = userUtils.getUser();
        return Result.success(user);
    }

    @GetMapping("/permission/menus")
    public Result<List<MenuVo>> permissionMenus() {
        DragonUser user = userUtils.getUser();
        List<MenuVo> menuVos = userService.getPermissionMenus(user.getAuthorities());
        return Result.success(menuVos);
    }

}
