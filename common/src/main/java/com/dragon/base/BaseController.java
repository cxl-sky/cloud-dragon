package com.dragon.base;

import com.dragon.exception.BaseException;
import com.dragon.exception.SystemErrorType;
import com.dragon.pojo.DragonUser;
import com.dragon.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月20日 下午11:49
 */
public class BaseController {

    @Autowired
    private UserUtils userUtils;

    /**
     * 时间日期格式转换
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
    }

    /**
     * 获取当前登陆用户信息
     *
     * @return
     */
    protected DragonUser getUser() {
        DragonUser user = userUtils.getUser();
        if (user == null) {
            throw new BaseException(SystemErrorType.ACCOUNT_EXPIRED);
        }
        return user;
    }
    /**
     *
     * 获取当前登陆用户信息
     *
     * @return
     */
    protected Long getUserId() {
        DragonUser user = getUser();
        return user.getId();
    }
    /**
     *
     * 获取当前登陆用户信息
     *
     * @return
     */
    protected String getUserName() {
        DragonUser user = getUser();
        return user.getUsername();
    }
}
