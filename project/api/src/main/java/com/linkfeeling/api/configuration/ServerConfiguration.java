package com.linkfeeling.api.configuration;


import com.linkfeeling.api.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ServerConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        SessionInterceptor sessionInterceptor = new SessionInterceptor();
        InterceptorRegistration sessionRegistry = registry.addInterceptor(sessionInterceptor);
        // 拦截路径
        sessionRegistry.addPathPatterns("/**");

        // exclude gym
        sessionRegistry.excludePathPatterns("/api/*");
    }

    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }
}