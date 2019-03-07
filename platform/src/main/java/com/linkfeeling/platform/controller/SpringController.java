package com.linkfeeling.platform.controller;

import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(SpringController.class);

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
        String name =  methodParameter.getParameterName();
        if(name.chars().anyMatch(item->item>='A'&& item<='Z')){
            if(methodParameter.getDeclaringClass() != HttpServletRequest.class){
                logger.error(methodParameter.getDeclaringClass().getName()+"#"+methodParameter.getMethod().getName()+"@"+name +" [not_snack]");
            }
        }
        return name;
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
