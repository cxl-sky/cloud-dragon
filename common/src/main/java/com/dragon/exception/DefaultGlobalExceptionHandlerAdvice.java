package com.dragon.exception;

import com.dragon.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * @author chenxiaolong
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MultipartException.class})
    public Result<String> uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return Result.fail(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result<String> serviceException(MethodArgumentNotValidException ex) {
        log.error("service exception:{}", ex.getMessage());
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {BaseException.class})
    public Result<String> baseException(BaseException ex) {
        log.error("base exception:{}", ex.getMessage());
        return Result.fail(ex.getErrorType());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception() {
        return Result.fail();
    }

}