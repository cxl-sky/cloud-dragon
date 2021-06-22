package com.dragon.user.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dragon.base.BaseController;
import com.dragon.pojo.DragonUser;
import com.dragon.response.Result;
import com.dragon.user.client.entity.Menu;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.entity.RoleMenu;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.server.service.MenuService;
import com.dragon.user.server.service.RoleMenuService;
import com.dragon.user.server.service.RoleService;
import com.dragon.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月19日 上午9:09
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/info")
    public Result<DragonUser> getCurrentUserInfo() {
        DragonUser user = userUtils.getUser();
        return Result.success(user);
    }

    @GetMapping("permission/menus")
    public Result<List<MenuVo>> permissionMenus() {
        DragonUser user = userUtils.getUser();
        return Result.success(getMenus(user));
    }

    private List<MenuVo> getMenus(DragonUser user) {
        Set<String> roles = user.getAuthorities();
        List<Role> roleList = roleService.list(new LambdaQueryWrapper<Role>().in(Role::getRoleName, roles));
        if (CollectionUtils.isEmpty(roleList)) {
            return new ArrayList<>();
        }
        List<RoleMenu> roleMenuList = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>()
                .in(RoleMenu::getRoleId, roleList.stream().map(Role::getRoleId).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }
        List<Menu> menuList = menuService.list(new LambdaQueryWrapper<Menu>()
                .in(Menu::getMenuId, roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet())));
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
