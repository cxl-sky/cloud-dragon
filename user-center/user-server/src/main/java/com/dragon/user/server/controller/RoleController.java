package com.dragon.user.server.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.base.BaseController;
import com.dragon.response.Result;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.query.RolePageQuery;
import com.dragon.user.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/page")
    public Result<Page<Role>> page(RolePageQuery rolePageQuery) {
        Page<Role> data = roleService.selectPage(rolePageQuery);
        return Result.success(data);
    }

}

