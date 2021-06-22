package com.dragon.user.server.service.impl;

import com.dragon.user.client.entity.Api;
import com.dragon.user.server.mapper.ApiMapper;
import com.dragon.user.server.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
