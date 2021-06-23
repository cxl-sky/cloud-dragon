package com.dragon.user.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.base.BaseController;
import com.dragon.pojo.DragonUser;
import com.dragon.response.Result;
import com.dragon.user.client.query.UserPageQuery;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.client.vo.UserVo;
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
    private UserService userService;

    @GetMapping("/page")
    public Result<Page<UserVo>> page(UserPageQuery userPageQuery) {
        Page<UserVo> data = userService.selectPage(userPageQuery);
        return Result.success(data);
    }

    @GetMapping("/info")
    public Result<UserVo> getCurrentUserInfo() {
        UserVo userVo = userService.getCurrentUserInfo();
        return Result.success(userVo);
    }

}
