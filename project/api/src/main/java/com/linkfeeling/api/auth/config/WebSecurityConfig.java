package com.linkfeeling.api.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.api.auth.handler.OnAccessDeniedHandler;
import com.linkfeeling.api.auth.handler.OnAuthFailHandler;
import com.linkfeeling.api.auth.handler.OnAuthSuccessHandler;
import com.linkfeeling.api.auth.handler.OnNoLoginHandler;
import com.linkfeeling.api.auth.user.manager.AllUserManager;
import com.linkfeeling.api.auth.user.manager.IUserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.util.DigestUtils;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AllUserManager userManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/api/platform/gym_*/*_me").hasAnyAuthority(IUserAuthority.GYM_ADMIN)
                .mvcMatchers("/api/platform/gym_*/*_group").hasAuthority(IUserAuthority.GYM_GROUP)
                .mvcMatchers(
                        "/api/account/system_user/*",
                        "/api/account/gym_group_user/*",
                        "/api/account/gym_admin_user/*",
                        "/api/platform/gym_*/*"
                ).hasAuthority(IUserAuthority.SYSTEM)
                .and()
                .csrf().disable()
                // 跨域支持
                .cors().and()
                .formLogin().loginPage("/api/account/platform/login")
                .usernameParameter("name")
                .passwordParameter("password")
                .failureHandler(onAuthFailHandler())
                .successHandler(onAuthSuccessHandler());
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(noLoginHandler());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userManager)
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()).equals(encodedPassword);
                    }
                });

    }

    @Bean
    public AllUserManager allUserManager(){
        return new AllUserManager();
    }

    public AccessDeniedHandler accessDeniedHandler(){
        return new OnAccessDeniedHandler(objectMapper);
    }

    public OnNoLoginHandler noLoginHandler(){
        return new OnNoLoginHandler(objectMapper);
    }

    public OnAuthFailHandler onAuthFailHandler(){
        return new OnAuthFailHandler(objectMapper);
    }

    public OnAuthSuccessHandler onAuthSuccessHandler(){
        return new OnAuthSuccessHandler(objectMapper,userManager);
    }
}
