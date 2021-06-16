package com.dragon.gateway.exception;

import com.dragon.exception.DefaultGlobalExceptionHandlerAdvice;
import com.dragon.exception.SystemErrorType;
import com.dragon.response.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author chenxiaolong
 */
@Slf4j
@RestControllerAdvice
public class GateWayExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

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

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return Result.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handle(SignatureException ex) {
        log.error("SignatureException:{}", ex.getMessage());
        return Result.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handle(MalformedJwtException ex) {
        log.error("MalformedJwtException:{}", ex.getMessage());
        return Result.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.fail();
    }

}
