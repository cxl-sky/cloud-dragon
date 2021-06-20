package com.dragon.authserver.exception;

import com.dragon.exception.SystemErrorType;
import com.dragon.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ResponseBody
@Order(0)
public class Oauth2ExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<?> handleUsernameNotFoundException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.USERNAME_NOT_FOUND);
    }

    @ExceptionHandler(DisabledException.class)
    public Result<?> handleDisabledException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.USERNAME_DISABLED);
    }

    @ExceptionHandler(AccountExpiredException.class)
    public Result<?> handleAccountExpiredException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.ACCOUNT_EXPIRED);
    }

    @ExceptionHandler(LockedException.class)
    public Result<?> handleLockedException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.ACCOUNT_LOCKED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> handleAccessDeniedException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.ACCESS_DENIED);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public Result<?> handleInvalidRequestException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.INVALID_REQUEST);
    }

    @ExceptionHandler(InvalidGrantException.class)
    public Result<?> handleInvalidGrantException(Exception e) {
        log.error(e.getLocalizedMessage());
        return new Result<>(SystemErrorType.INVALID_GRANT);
    }
}
