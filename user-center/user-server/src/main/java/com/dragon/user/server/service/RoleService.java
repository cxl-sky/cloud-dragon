package com.dragon.user.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.query.RolePageQuery;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页
     * @param rolePageQuery
     * @return
     */
    Page<Role> selectPage(RolePageQuery rolePageQuery);
}
