package com.dragon.user.server.controller;

import cn.hutool.json.JSONObject;
import com.dragon.pojo.DragonUser;
import com.dragon.response.Result;
import com.dragon.user.client.entity.Menu;
import com.dragon.user.client.vo.MenuVo;
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
import java.util.stream.Collectors;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月19日 上午9:09
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/info")
    public Result<DragonUser> getCurrentUserInfo() {
        DragonUser user = userUtils.getUser();
        return Result.success(user);
    }

    @GetMapping("permission/menus")
    public Result<List<MenuVo>> permissionMenus() {
        DragonUser user = userUtils.getUser();
        // todo: 根据用户获取该用户角色对应的菜单
        return Result.success(getMenus());
    }

    private List<MenuVo> getMenus() {

        Menu menu = new Menu(1, null, "/index", "index", "hhhh/ssss",
                1, null, "测试一下", "table", 1);
        Menu menu1 = new Menu(2, 1, "/index1", "index1", "hhhh/ssss1",
                1, null, "测试一下1", "table1", 1);
        Menu menu2 = new Menu(3, 1, "/index2", "index2", "hhhh/ssss2",
                1, null, "测试一下2", "table2", 2);

        Menu menu3 = new Menu(4, null, "/index3", "index3", "hhhh/ssss3",
                1, null, "测试一下3", "table3", 1);
        Menu menu4 = new Menu(5, 4, "/index4", "index4", "hhhh/ssss4",
                1, null, "测试一下4", "table4", 1);
        Menu menu5 = new Menu(6, 4, "/index5", "index5", "hhhh/ssss5",
                1, null, "测试一下5", "table5", 2);

        // 测试菜单，实际应为数据库获取
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);
        menuList.add(menu5);

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
