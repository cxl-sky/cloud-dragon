package com.dragon.generator.exception;

import com.dragon.exception.DefaultGlobalExceptionHandlerAdvice;
import com.dragon.exception.SystemErrorType;
import com.dragon.response.Result;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author chenxiaolong
 * @description
 * @date 2021年06月21日 上午10:03
 */
@ControllerAdvice
@Slf4j
public class CodeGeneratorGlobalException extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MySQLSyntaxErrorException.class})
    public Result<String> mySqlSyntaxErrorException(MySQLSyntaxErrorException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return Result.fail(SystemErrorType.MYSQL_SYNTAX_ERROR, ex.getMessage());
    }
}
