package com.xlhj.baojia.filter;

import cn.hutool.core.util.StrUtil;
import com.xlhj.baojia.entity.vo.LoginUser;
import com.xlhj.baojia.util.JwtTokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lcj
 * @Date: 2020/10/22 9:45
 * @Description: token过滤器，验证token有效性
 * @Version: 0.0.1
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        LoginUser loginUser = JwtTokenUtils.getLoginUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (loginUser != null && authentication != null) {
            JwtTokenUtils.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
