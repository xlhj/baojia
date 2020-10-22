package com.xlhj.baojia.handler;

import com.alibaba.fastjson.JSON;
import com.xlhj.baojia.common.ResultData;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtLogoutSuccessHandler
 * @Description 登出成功处理类
 * @Author liucaijing
 * @Date 2020/10/21 21:59
 * @Version 1.0
 */
@Configuration
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResultData.sendStr(response, JSON.toJSONString(ResultData.ok()));
    }
}
