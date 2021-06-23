package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.query.RolePageQuery;
import com.dragon.user.server.mapper.RoleMapper;
import com.dragon.user.server.service.RoleService;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public Page<Role> selectPage(RolePageQuery rolePageQuery) {
        Page<Role> page = new Page<>(rolePageQuery.getPageNum(), rolePageQuery.getPageSize());
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(rolePageQuery.getRoleNameLike())) {
            queryWrapper.like(Role::getRoleName, rolePageQuery.getRoleNameLike());
        }
        return page(page, queryWrapper);
    }
}
