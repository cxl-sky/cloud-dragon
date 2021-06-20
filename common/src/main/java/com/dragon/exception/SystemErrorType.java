package com.dragon.exception;

import lombok.Getter;

/**
 * @author chenxiaolong
 */

@Getter
public enum SystemErrorType implements ErrorType {

    /**
     * 错误类型 code msg
     */
    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_ERROR("010500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),

    ARGUMENT_NOT_VALID("020000", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("020010", "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY("030000","唯一键冲突"),

    // 认证相关
    USERNAME_NOT_FOUND("050000", "输入账户不存在"),
    USERNAME_DISABLED("050001", "用户已禁用"),
    INVALID_TOKEN("050002", "无效token"),
    ACCOUNT_EXPIRED("050003", "账户登录失效"),
    ACCOUNT_LOCKED("050004", "账户已被锁定"),
    ACCESS_DENIED("050005", "拒绝访问"),
    INVALID_REQUEST("050006", "错误的请求"),
    INVALID_GRANT("050007", "用户或密码错误"),

    ;

    /**
     * 错误类型码
     */
    private final String code;
    /**
     * 错误类型描述信息
     */
    private final String msg;


    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
