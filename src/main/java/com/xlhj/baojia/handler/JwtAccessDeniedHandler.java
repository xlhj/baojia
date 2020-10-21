package com.xlhj.baojia.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtAccessDeniedHandler
 * @Description 无权限访问拦截器
 * @Author liucaijing
 * @Date 2020/10/21 20:20
 * @Version 1.0
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
}
