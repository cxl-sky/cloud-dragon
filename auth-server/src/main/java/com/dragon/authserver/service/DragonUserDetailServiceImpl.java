package com.dragon.authserver.service;

import com.dragon.authserver.vo.DragonUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("no user found");
        } else {

            String password = passwordEncoder.encode("123456");

            return new org.springframework.security.core.userdetails.User(
                    username,
                    password,
                    true,
                    true,
                    true,
                    true,
                    this.obtainGrantedAuthorities());
        }
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities() {
        String role = "admin";
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));
//        Set<Role> roles = roleService.queryUserRolesByUserId(user.getId());
//        log.info("user:{},roles:{}", user.getUsername(), roles);
        return authorities;
    }

    /**
     * 这里模拟根据手机号查询用户
     *
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        if (mobile == null) {
            throw new UsernameNotFoundException("not found mobile user:" + mobile);
        }
        String role = "admin";
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        String password = passwordEncoder.encode("123456");
        return new DragonUser("1", mobile, password, "test", true, authorities);

    }
}
