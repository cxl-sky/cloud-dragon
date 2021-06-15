package com.dragon.cmn.exception;

/**
 * @author chenxiaolong
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMsg();
}
