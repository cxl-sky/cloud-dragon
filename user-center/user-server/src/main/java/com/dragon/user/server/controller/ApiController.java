package com.dragon.user.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.base.BaseController;
import com.dragon.response.Result;
import com.dragon.user.client.entity.Api;
import com.dragon.user.client.query.ApiPageQuery;
import com.dragon.user.server.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 接口管理 前端控制器
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/apis")
public class ApiController extends BaseController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/page")
    public Result<Page<Api>> page(ApiPageQuery apiPageQuery) {
        Page<Api> data = apiService.selectPage(apiPageQuery);
        return Result.success(data);
    }

}

