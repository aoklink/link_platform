package com.linkfeeling.api.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.api.auth.user.manager.AllUserManager;
import com.linkfeeling.api.auth.user.manager.bean.AuthoritiesUser;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class OnAuthSuccessHandler implements AuthenticationSuccessHandler {
    private ObjectMapper objectMapper;

    private AllUserManager userManager;

    public OnAuthSuccessHandler(ObjectMapper objectMapper, AllUserManager userManager) {
        this.objectMapper = objectMapper;
        this.userManager = userManager;
    }

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
}
