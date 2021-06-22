package com.dragon.user.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.user.client.entity.Api;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.user.client.query.ApiPageQuery;

/**
 * <p>
 * 接口管理 服务类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
public interface ApiService extends IService<Api> {

    /**
     * 分页
     * @param apiPageQuery
     * @return
     */
    Page<Api> selectPage(ApiPageQuery apiPageQuery);
}
