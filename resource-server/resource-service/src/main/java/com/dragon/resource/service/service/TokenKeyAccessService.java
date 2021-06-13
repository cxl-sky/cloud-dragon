package com.dragon.resource.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 接口权限控制
 *
 * @author cxl
 */
@Service("tokenKeyAccessService")
@Slf4j
public class TokenKeyAccessService {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

//    @Autowired
//    private UserAuthService userAuthService;

    /**
     * 根据接口地址判断是否有权限
     *
     * @param httpServletRequest
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication) {
//        List<Module> modules = userAuthService.getModulesByRoleNames(getUserRoleNames(authentication));
//        for (Module module : modules) {
//            if (antPathMatcher.match(module.getUrlPath(), httpServletRequest.getRequestURI())
//                    && module.getMethod().equalsIgnoreCase(httpServletRequest.getMethod())
//                    && StringUtils.isNotEmpty(module.getMethod())) {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * 获取用户角色信息
     *
     * @param authentication
     * @return
     */
    private List<String> getUserRoleNames(Authentication authentication) {
        Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
        List<String> roleNames = new ArrayList<>();
        while (iterator.hasNext()) {
            GrantedAuthority grantedAuthority = iterator.next();
            String authority = grantedAuthority.getAuthority();
            roleNames.add(authority);
        }
        return roleNames;
    }

}
