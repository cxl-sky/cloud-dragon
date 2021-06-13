package com.dragon.gateway.exception;

import com.dragon.cmn.exception.SystemErrorType;
import com.dragon.cmn.response.Result;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author chenxiaolong
 */
@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public Result<String> handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return Result.fail(SystemErrorType.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public Result<String> handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return Result.fail(SystemErrorType.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<String> handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return Result.fail(SystemErrorType.GATEWAY_NOT_FOUND_SERVICE);
    }
//
//    @ExceptionHandler(value = {ExpiredJwtException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Result handle(ExpiredJwtException ex) {
//        log.error("ExpiredJwtException:{}", ex.getMessage());
//        return Result.fail(SystemErrorType.INVALID_TOKEN);
//    }
//
//    @ExceptionHandler(value = {SignatureException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Result handle(SignatureException ex) {
//        log.error("SignatureException:{}", ex.getMessage());
//        return Result.fail(SystemErrorType.INVALID_TOKEN);
//    }
//
//    @ExceptionHandler(value = {MalformedJwtException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Result handle(MalformedJwtException ex) {
//        log.error("MalformedJwtException:{}", ex.getMessage());
//        return Result.fail(SystemErrorType.INVALID_TOKEN);
//    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(Throwable throwable) {
        Result<String> result = Result.fail();
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
