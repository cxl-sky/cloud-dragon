package com.dragon.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.entity.*;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.client.vo.UserVo;
import com.dragon.user.server.mapper.*;
import com.dragon.user.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ApiMapper apiMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserVo selectUserVoByUsername(String username) {
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            return null;
        }
        List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
        List<Role> roles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoles)) {
            roles = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getRoleId, userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet())));
        }
        return buildUserVo(user, roles);
    }

    @Override
    public boolean isHasPermission(PermissionDecideDto permissionDecideDto) {
        String url = permissionDecideDto.getUrl().replaceFirst("/", "");
        String serviceName = url.substring(0, url.indexOf("/"));
        String realUrl = url.replaceFirst(serviceName, "");
        String method = permissionDecideDto.getMethod().toLowerCase(Locale.ROOT);
        // 如果接口不需要鉴权直接放行
        List<Api> apis = apiMapper.selectList(new LambdaQueryWrapper<Api>()
                .eq(Api::getService, serviceName)
                .eq(Api::getMethod, method)
                .eq(Api::getUrl, realUrl)
                .eq(Api::getNeedAuth, 0));
        if (!CollectionUtils.isEmpty(apis)) {
            return true;
        }

        // 走到这里说明接口需要鉴权
        List<String> roles = permissionDecideDto.getRoles();
        List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getRoleName, roles));
        if (CollectionUtils.isEmpty(roleList)) {
            return false;
        }
        List<RoleMenu> roleMenuList = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>()
                .in(RoleMenu::getRoleId, roleList.stream().map(Role::getRoleId).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return false;
        }
        List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                .in(Menu::getMenuId, roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(menuList)) {
            return false;
        }
        List<String> permList = new ArrayList<>();
        for (Menu menu : menuList) {
            String[] split = menu.getPerms().split(",");
            List<String> perms = Arrays.stream(split).collect(Collectors.toList());
            permList.addAll(perms);
        }
        for (String perm : permList) {
            List<Api> authApiList = apiMapper.selectList(new LambdaQueryWrapper<Api>()
                    .eq(Api::getService, serviceName)
                    .eq(Api::getMethod, method)
                    .eq(Api::getUrl, realUrl)
                    .like(Api::getPerms, perm));
            if (!CollectionUtils.isEmpty(authApiList)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MenuVo> getPermissionMenus(Set<String> roles) {
        List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getRoleName, roles));
        if (CollectionUtils.isEmpty(roleList)) {
            return new ArrayList<>();
        }
        List<RoleMenu> roleMenuList = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>()
                .in(RoleMenu::getRoleId, roleList.stream().map(Role::getRoleId).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }
        List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
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

    private UserVo buildUserVo(User user, List<Role> roles) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRoles(roles);
        return userVo;
    }
}
