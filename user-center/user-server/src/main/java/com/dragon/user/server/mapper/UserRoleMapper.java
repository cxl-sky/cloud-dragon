package com.dragon.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.user.client.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
