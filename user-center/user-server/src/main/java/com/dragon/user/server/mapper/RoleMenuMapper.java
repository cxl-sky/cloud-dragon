package com.dragon.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.user.client.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}
