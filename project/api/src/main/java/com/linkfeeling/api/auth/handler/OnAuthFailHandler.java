package com.linkfeeling.api.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnAuthFailHandler implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper;

    public OnAuthFailHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseUtil.newResponseWithDesc(ResponseDesc.PASSWORD_ERROR,exception.getMessage())));
    }
}
