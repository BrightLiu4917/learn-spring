package com.liuweiliang.demo1.config;

import com.liuweiliang.demo1.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置：注册JWT拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                // 1. 拦截所有/api/v1/**路径的请求（需要登录的接口）
                .addPathPatterns("/api/v1/auth/**")
                // 2. 排除登录接口（无需登录即可访问）
                .excludePathPatterns("/api/v1/login");
    }
}