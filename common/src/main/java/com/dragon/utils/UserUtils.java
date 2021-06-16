package com.dragon.utils;

import com.alibaba.fastjson.JSON;
import com.dragon.constants.SystemConstant;
import com.dragon.pojo.DragonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月16日 下午4:13
 */
@Component
public class UserUtils {

    @Autowired
    private HttpServletRequest request;

    public DragonUser getUser() {
        String userInfo = request.getHeader(SystemConstant.X_CLIENT_TOKEN_USER);
        if (StringUtils.isEmpty(userInfo)) {
            return null;
        }
        return JSON.parseObject(userInfo, DragonUser.class);
    }
}
