package com.dragon.user.server.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.base.BaseController;
import com.dragon.pojo.DragonUser;
import com.dragon.response.Result;
import com.dragon.user.client.entity.Api;
import com.dragon.user.client.query.ApiPageQuery;
import com.dragon.user.client.query.MenuPageQuery;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.server.service.MenuService;
import com.dragon.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/menus")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserUtils userUtils;

    @GetMapping("/page")
    public Result<Page<MenuVo>> page(MenuPageQuery menuPageQuery) {
        Page<MenuVo> data = menuService.selectPage(menuPageQuery);
        return Result.success(data);
    }

    @GetMapping("/permission")
    public Result<List<MenuVo>> permissionMenus() {
        DragonUser user = userUtils.getUser();
        List<MenuVo> menuVos = menuService.getPermissionMenus(user.getAuthorities());
        return Result.success(menuVos);
    }

}

