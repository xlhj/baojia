package com.xlhj.baojia.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName GlobalCorsConfig
 * @Description 跨域全局配置类
 * @Author liucaijing
 * @Date 2020/10/28 0:03
 * @Version 1.0
 */
@Slf4j
@Configuration
public class GlobalCorsConfig {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Bean
    public CorsFilter corsFilter() {
        log.info(this.allowedOrigin);
        //添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允许的域，不能写*，写具体的前端服务器
        config.addAllowedOrigin(allowedOrigin);
        //是否发送cookie信息
        config.setAllowCredentials(true);
        //允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //允许的头信息
        config.addAllowedHeader("*");
        //添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);
        //返回新的CorsFilter
        return new CorsFilter(configurationSource);
    }
}
