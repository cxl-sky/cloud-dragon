package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.entity.Role;
import com.dragon.user.server.mapper.RoleMapper;
import com.dragon.user.server.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
