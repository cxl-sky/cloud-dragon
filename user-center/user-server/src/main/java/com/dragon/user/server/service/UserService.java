package com.dragon.user.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.entity.User;
import com.dragon.user.client.query.UserPageQuery;
import com.dragon.user.client.vo.MenuVo;
import com.dragon.user.client.vo.UserVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author chenxiaolong
 * @since 2021-06-21
 */
public interface UserService extends IService<User> {

    /**
     * 根据名字查找
     * @param username
     * @return
     */
    UserVo selectUserVoByUsername(String username);

    /**
     * 判断是否有权限
     * @param permissionDecideDto
     * @return
     */
    boolean isHasPermission(PermissionDecideDto permissionDecideDto);

    /**
     * 获取当前登录用户信息
     * @return
     */
    UserVo getCurrentUserInfo();

    /**
     * 分页
     * @param userPageQuery
     * @return
     */
    Page<UserVo> selectPage(UserPageQuery userPageQuery);
}
