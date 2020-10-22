package com.xlhj.baojia.handler;

import com.xlhj.baojia.common.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: lcj
 * @Date: 2020/10/22 13:39
 * @Description: 全局异常处理类
 * @Version: 0.0.1
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResultData handleAuthorizationException(AccessDeniedException e) {
        return ResultData.error(HttpStatus.FORBIDDEN.value(), "没有访问权限!");
    }
}
