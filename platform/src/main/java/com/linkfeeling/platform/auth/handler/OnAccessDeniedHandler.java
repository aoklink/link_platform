package com.linkfeeling.platform.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnAccessDeniedHandler implements AccessDeniedHandler {

    private ObjectMapper objectMapper;

    public OnAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseUtil.newResponseWithDesc(ResponseDesc.ACCESS_DENIED,"access denied on "+request.getServletPath())));
    }
}
