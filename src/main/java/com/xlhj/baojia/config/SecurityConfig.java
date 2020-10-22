package com.xlhj.baojia.config;

import com.xlhj.baojia.filter.JwtAuthenticationTokenFilter;
import com.xlhj.baojia.handler.JwtAuthenticationEntryPoint;
import com.xlhj.baojia.handler.JwtLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: lcj
 * @Date: 2020/10/22 9:43
 * @Description: spring security配置类
 * @Version: 0.0.1
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**自定义用户认证*/
    @Autowired
    private UserDetailsService userDetailsService;
    /**认证失败处理类*/
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    /**退出成功处理类*/
    @Autowired
    private JwtLogoutSuccessHandler logoutSuccessHandler;
    /**token认证过滤器*/
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;
    /**跨域过滤器*/
    //@Autowired
    //private CorsFilter corsFilter;

    /**
     * 配置AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置权限认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/login").anonymous()
                .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .anyRequest().authenticated().and().headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        //http.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    /**
     * 配置加解密
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置身份认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
