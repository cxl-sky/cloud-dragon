package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.entity.Menu;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.entity.RoleMenu;
import com.dragon.user.client.query.MenuPageQuery;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.server.mapper.MenuMapper;
import com.dragon.user.server.mapper.RoleMapper;
import com.dragon.user.server.mapper.RoleMenuMapper;
import com.dragon.user.server.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-22
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private static final String ADMIN_ROLE = "admin";
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Page<MenuVo> selectPage(MenuPageQuery menuPageQuery) {
        Page<Menu> page = new Page<>(menuPageQuery.getPageNum(), menuPageQuery.getPageSize());
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(menuPageQuery.getTitleLike())) {
            queryWrapper.like(Menu::getTitle, menuPageQuery.getTitleLike());
        }
        if (StringUtils.isNotBlank(menuPageQuery.getPermsLike())) {
            queryWrapper.like(Menu::getPerms, menuPageQuery.getPermsLike());
        }
        if (menuPageQuery.getStatus() != null) {
            queryWrapper.like(Menu::getStatus, menuPageQuery.getStatus());
        }
        if (menuPageQuery.getType() != null) {
            queryWrapper.like(Menu::getType, menuPageQuery.getType());
        }
        queryWrapper.eq(Menu::getParentId, 0);
        page = page(page, queryWrapper);

        List<MenuVo> menuVos = buildVo(castMenu2Vo(page.getRecords()));

        Page<MenuVo> menuVoPage = new Page<>();
        BeanUtils.copyProperties(page, menuVoPage);
        menuVoPage.setRecords(menuVos);

        return menuVoPage;
    }

    @Override
    public List<MenuVo> getPermissionMenus(Set<String> roles) {
        List<Menu> menuList;
        if (roles.contains(ADMIN_ROLE)) {
            menuList = list();
        } else {
            List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getRoleName, roles));
            if (CollectionUtils.isEmpty(roleList)) {
                return new ArrayList<>();
            }
            List<RoleMenu> roleMenuList = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>()
                    .in(RoleMenu::getRoleId, roleList.stream().map(Role::getRoleId).collect(Collectors.toSet())));
            if (CollectionUtils.isEmpty(roleMenuList)) {
                return new ArrayList<>();
            }
            menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                    .in(Menu::getMenuId, roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet())));
        }
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }

        List<MenuVo> allMenuList = menuList.stream().map(m -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(m, menuVo);
            return menuVo;
        }).collect(Collectors.toList());

        List<MenuVo> topMenuVos = menuList.stream()
                .filter(m -> m.getParentId() == null || m.getParentId() == 0)
                .map(m -> {
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(m, menuVo);
                    return menuVo;
                })
                .sorted(Comparator.comparing(MenuVo::getOrderNum))
                .collect(Collectors.toList());

        return buildVo(topMenuVos, allMenuList);
    }

    private List<MenuVo> castMenu2Vo(List<Menu> menuList) {
        return menuList.stream().map(m -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(m, menuVo);
            return menuVo;
        }).collect(Collectors.toList());
    }

    private List<MenuVo> buildVo(List<MenuVo> topMenuVos) {
        for (MenuVo menuVo : topMenuVos) {
            List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId, menuVo.getMenuId()));
            if (!CollectionUtils.isEmpty(menuList)) {
                List<MenuVo> children = castMenu2Vo(menuList);
                menuVo.setChildren(children);
                buildVo(children);
            }
        }
        return topMenuVos;
    }


    private List<MenuVo> buildVo(List<MenuVo> topMenuVos, List<MenuVo> allMenus) {
        for (MenuVo menuVo : topMenuVos) {
            List<MenuVo> childrenMenus = allMenus.stream().filter(menu -> menu.getParentId() != null && menu.getParentId().equals(menuVo.getMenuId()))
                    .sorted(Comparator.comparing(MenuVo::getOrderNum)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(childrenMenus)) {
                menuVo.setChildren(childrenMenus);
                buildVo(childrenMenus, allMenus);
            }
        }
        return topMenuVos;
    }
}
