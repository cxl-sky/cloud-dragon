package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.entity.UserRole;
import com.dragon.user.server.mapper.UserRoleMapper;
import com.dragon.user.server.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
