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
        sessionRegistry.excludePathPatterns("/api/upload");
        sessionRegistry.excludePathPatterns("/api/uwb/data");
        sessionRegistry.excludePathPatterns("/api/gym/data");


        //exclude platform
        sessionRegistry.excludePathPatterns("/api/platform/bracelet/add");
        sessionRegistry.excludePathPatterns("/api/platform/bracelet/bind");
        sessionRegistry.excludePathPatterns("/api/platform/bracelet/unbind");
        sessionRegistry.excludePathPatterns("/api/platform/bracelet/delete");

        //exclude account
        sessionRegistry.excludePathPatterns("/api/user/check_smscode");
        sessionRegistry.excludePathPatterns("/api/user/check_register");
        sessionRegistry.excludePathPatterns("/api/user/register");
        sessionRegistry.excludePathPatterns("/api/user/login");
        sessionRegistry.excludePathPatterns("/api/user/get_code");
        sessionRegistry.excludePathPatterns("/api/user/login_out");
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