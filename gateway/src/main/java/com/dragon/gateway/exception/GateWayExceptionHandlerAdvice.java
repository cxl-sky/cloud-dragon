package com.dragon.gateway.exception;

import com.dragon.exception.DefaultGlobalExceptionHandlerAdvice;
import com.dragon.exception.SystemErrorType;
import com.dragon.response.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return Result.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handle(AccessDeniedException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return Result.fail(SystemErrorType.ACCESS_DENIED);
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
        return Result.fail(ex.getMessage());
    }

    public Map<String, Object> handle(Throwable throwable) {
        if (throwable.getCause() instanceof AccessDeniedException) {
            System.out.println("''''''''''''''''''''''''");
        }
        Result<String> result = null;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (throwable instanceof ResponseStatusException) {
            ResponseStatusException exception = (ResponseStatusException) throwable;
            result = handle(exception);
            status = exception.getStatus();
        } else if (throwable instanceof ConnectTimeoutException) {
            ConnectTimeoutException exception = (ConnectTimeoutException) throwable;
            result = handle(exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (throwable instanceof ExpiredJwtException) {
            ExpiredJwtException exception = (ExpiredJwtException) throwable;
            result = handle(exception);
            status = HttpStatus.UNAUTHORIZED;
        } else if (throwable instanceof AccessDeniedException) {
            AccessDeniedException exception = (AccessDeniedException) throwable;
            result = handle(exception);
            status = HttpStatus.UNAUTHORIZED;
        } else if (throwable instanceof SignatureException) {
            SignatureException exception = (SignatureException) throwable;
            result = handle(exception);
            status = HttpStatus.BAD_REQUEST;
        } else if (throwable instanceof MalformedJwtException) {
            MalformedJwtException exception = (MalformedJwtException) throwable;
            result = handle(exception);
            status = HttpStatus.UNAUTHORIZED;
        } else if (throwable instanceof RuntimeException) {
            RuntimeException exception = (RuntimeException) throwable;
            result = handle(exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("result", result);
        map.put("status", status);
        return map;
    }
}
