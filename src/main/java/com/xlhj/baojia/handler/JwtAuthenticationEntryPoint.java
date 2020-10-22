package com.xlhj.baojia.handler;

import com.alibaba.fastjson.JSON;
import com.xlhj.baojia.common.ResultCode;
import com.xlhj.baojia.common.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtAuthenticationEntryPoint
 * @Description 认证失败处理类
 * @Author liucaijing
 * @Date 2020/10/21 20:22
 * @Version 1.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String message = "无权限访问资源：" + request.getRequestURI();
        ResultData.sendStr(response, JSON.toJSONString(ResultData.error(ResultCode.UNAUTHORIZED, message)));
    }
}
