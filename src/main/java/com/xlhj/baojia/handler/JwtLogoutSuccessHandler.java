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
 * @Author: lcj
 * @Date: 2020/10/22 14:28
 * @Description: 退出成功处理类
 * @Version: 0.0.1
 */
@Configuration
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResultData.sendStr(response, JSON.toJSONString(ResultData.ok()));
    }
}
