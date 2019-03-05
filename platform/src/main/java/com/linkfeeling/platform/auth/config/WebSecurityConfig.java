package com.linkfeeling.platform.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.platform.auth.user.manager.AllUserManager;
import com.linkfeeling.platform.bean.interactive.response.AuthoritiesUser;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AllUserManager userManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/api/gym*/**")
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(objectMapper.writeValueAsString(ResponseUtil.newResponseWithDesc(ResponseDesc.PASSWORD_ERROR,exception.getMessage())));
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=UTF-8");
                        AuthoritiesUser authoritiesUser =
                                new AuthoritiesUser(userManager.getUserToResponse(authentication.getName()),
                                authentication.getAuthorities().stream()
                                        .map(authenticationItem->authenticationItem.getAuthority())
                                        .collect(Collectors.toList()));
                        response.getWriter().write(objectMapper.writeValueAsString(ResponseUtil.newSuccess(authoritiesUser)));
                    }
                });
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
}
