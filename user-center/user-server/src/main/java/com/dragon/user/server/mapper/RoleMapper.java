package com.dragon.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.user.client.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
