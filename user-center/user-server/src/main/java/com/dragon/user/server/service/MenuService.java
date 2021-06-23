package com.dragon.user.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.entity.Menu;
import com.dragon.user.client.query.MenuPageQuery;
import com.dragon.user.client.vo.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
public interface MenuService extends IService<Menu> {

    /**
     * 分页
     * @param menuPageQuery
     * @return
     */
    Page<MenuVo> selectPage(MenuPageQuery menuPageQuery);

    /**
     * 获取权限菜单
     * @param authorities
     * @return
     */
    List<MenuVo> getPermissionMenus(Set<String> authorities);
}
