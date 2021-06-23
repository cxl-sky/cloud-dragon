package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.entity.Api;
import com.dragon.user.client.query.ApiPageQuery;
import com.dragon.user.server.mapper.ApiMapper;
import com.dragon.user.server.service.ApiService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * <p>
 * 接口管理 服务实现类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

    @Override
    public Page<Api> selectPage(ApiPageQuery apiPageQuery) {
        Page<Api> page = new Page<>(apiPageQuery.getPageNum() , apiPageQuery.getPageSize());
        LambdaQueryWrapper<Api> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(apiPageQuery.getUrlLike())) {
            queryWrapper.like(Api::getUrl, apiPageQuery.getUrlLike());
        }
        if (StringUtils.isNotBlank(apiPageQuery.getMethod())) {
            queryWrapper.eq(Api::getMethod, apiPageQuery.getMethod().toUpperCase(Locale.ROOT));
        }
        return page(page, queryWrapper);
    }
}
