package com.dragon.resource.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.pojo.AuthUser;
import com.dragon.pojo.DragonUser;
import com.dragon.resource.service.service.AuthenticationService;
import com.dragon.user.client.UserClient;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author chenxiaolong
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";
    @Autowired
    private UserClient userClient;
    /**
     * @param authRequest 访问的url,method
     * @return 有权限返回当前用户，网关将此用户设置到请求头中，供后续服务使用
     */
    @Override
    public String decide(HttpServletRequest authRequest) {
        log.info("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser principal = (AuthUser) authentication.getPrincipal();

        PermissionDecideDto permissionDecideDto = new PermissionDecideDto();
        permissionDecideDto.setMethod(authRequest.getMethod());
        permissionDecideDto.setUrl(authRequest.getServletPath());
        permissionDecideDto.setRoles(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        boolean hasPermission = userClient.hasPermission(permissionDecideDto);

        if (!hasPermission) {
            return null;
        }
        DragonUser dragonUser = new DragonUser();
        dragonUser.setId(principal.getId());
        dragonUser.setUsername(principal.getUsername());
        dragonUser.setAuthorities(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        dragonUser.setRemark(principal.getRemark());
        dragonUser.setAuthor(principal.getAuthor());

        return JSON.toJSONString(dragonUser);
    }

}
