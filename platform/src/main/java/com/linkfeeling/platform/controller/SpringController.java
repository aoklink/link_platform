package com.linkfeeling.platform.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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
//        List<String> urlPatterns =  objHandlerMethodMapping.getHandlerMethods().keySet().stream()
//                .map(requestMappingInfo -> requestMappingInfo.getPatternsCondition().getPatterns())
//                .flatMap(set->set.stream())
//                .sorted()
//                .collect(Collectors.toList());

        List<String> urlAPIList = objHandlerMethodMapping.getHandlerMethods().entrySet().stream()
                .map(entry->entry.getKey().getPatternsCondition().getPatterns().toString()+":"
                        + getParameterDescList(entry.getValue().getMethodParameters()))
                .collect(Collectors.toList());
        return ResponseUtil.newSuccess(urlAPIList);
    }

    private static String getParameterName(MethodParameter methodParameter){
        methodParameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
        PropertyNamingStrategy.SnakeCaseStrategy snakeCaseStrategy = new PropertyNamingStrategy.SnakeCaseStrategy();
        return snakeCaseStrategy.translate(methodParameter.getParameterName());
    }

    private static List<String> getParameterDescList(MethodParameter[] methodParameters){
        List<String> paramDescList =  new ArrayList<>();
        for (MethodParameter methodParameter : methodParameters){
            if(methodParameter.getParameterType() != HttpServletRequest.class){
                paramDescList.add(methodParameter.getParameterType().getSimpleName()+" "+getParameterName(methodParameter));
            }
        }
        return paramDescList;
    }
}
