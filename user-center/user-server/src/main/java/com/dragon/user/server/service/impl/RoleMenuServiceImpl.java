package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.entity.RoleMenu;
import com.dragon.user.server.mapper.RoleMenuMapper;
import com.dragon.user.server.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
