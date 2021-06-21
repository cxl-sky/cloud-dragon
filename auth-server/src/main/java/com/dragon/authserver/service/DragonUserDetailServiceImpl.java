package com.dragon.authserver.service;

import com.dragon.pojo.AuthUser;
import com.dragon.user.client.UserClient;
import com.dragon.user.client.entity.Role;
import com.dragon.user.client.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月12日 16:49
 */
@Component("DragonUserDetailService")
public class DragonUserDetailServiceImpl implements UserDetailsService {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserVo userVo = userClient.selectUserByUsername(username);
        if (userVo == null) {
            throw new UsernameNotFoundException("no user found");
        }
        return new AuthUser(
                userVo.getUserId(),
                userVo.getUsername(),
                userVo.getPassword(),
                userVo.getStatus() == 1,
                this.obtainGrantedAuthorities(userVo.getRoles()));
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
    }

    /**
     * 这里模拟根据手机号查询用户
     *
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        // todo
        return null;
    }
}
