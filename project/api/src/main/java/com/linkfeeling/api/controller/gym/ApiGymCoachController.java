package com.linkfeeling.api.controller.gym;

import com.linkfeeling.api.comsumer.GymPlatformConsumer;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/platform/gym_coach")
public class ApiGymCoachController {
    @Autowired
    private GymPlatformConsumer gymPlatformConsumer;

    private Map<String,APIProxy> apiProxyMap;

    private Map<String,APIWrapper> apiWrapperMap;

    @PostConstruct
    private void init(){
        apiProxyMap = new HashMap<>();
        apiProxyMap.put(ControllerActionContract.OPERATE.ADD,(requestJson -> gymPlatformConsumer.gymCoachAdd(requestJson)));
        apiProxyMap.put(ControllerActionContract.OPERATE.DELETE,(requestJson -> gymPlatformConsumer.gymCoachDelete(requestJson)));
        apiProxyMap.put(ControllerActionContract.OPERATE.UPDATE,(requestJson -> gymPlatformConsumer.gymCoachUpdate(requestJson)));
        apiProxyMap.put(ControllerActionContract.OPERATE.GET,(requestJson -> gymPlatformConsumer.gymCoachGet(requestJson)));
        apiProxyMap.put(ControllerActionContract.OPERATE.LIST,(requestJson -> gymPlatformConsumer.gymCoachListByGymId(requestJson)));

        apiProxyMap.put(ControllerActionContract.OPERATE.ADD_ME,(requestJson -> gymPlatformConsumer.gymCoachAdd(requestJson)));
    }

    @RequestMapping("/*")
    public Response proxy(HttpServletRequest request,@RequestBody String requestJson){
        String path = request.getServletPath();
        String operate = path.substring("/api/platform/gym_coach".length());
        APIWrapper apiWrapper = apiWrapperMap.get(operate);
        if(apiWrapper!=null){
            requestJson = apiWrapper.wrap(request, requestJson);
        }
        APIProxy apiProxy = apiProxyMap.get(operate);
        if(apiProxy==null){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,path+" not found");
        }else{
            return apiProxy.proxy(requestJson);
        }
    }

    private interface APIProxy{
        Response proxy(@RequestBody String requestJson);
    }

    /**
     * 为请求的Json增加额外参数
     */
    private interface APIWrapper{
        String wrap(HttpServletRequest request,@RequestBody String requestJson);
    }
}
