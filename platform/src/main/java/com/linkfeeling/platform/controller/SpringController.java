package com.linkfeeling.platform.controller;

import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/spring")
public class SpringController {
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/mapping")
    public Response printMapping(){
        AbstractHandlerMethodMapping<RequestMappingInfo> objHandlerMethodMapping =
                (AbstractHandlerMethodMapping<RequestMappingInfo>)
                        applicationContext.getBean("requestMappingHandlerMapping");
        List<String> urlPatterns = new ArrayList<>();
        Set<RequestMappingInfo> mappingInfoSet = objHandlerMethodMapping.getHandlerMethods().keySet();
        mappingInfoSet.stream()
                .map(requestMappingInfo -> requestMappingInfo.getPatternsCondition().getPatterns())
                .forEach(item->urlPatterns.addAll(item));
        return ResponseUtil.newSuccess(urlPatterns);
    }
}
