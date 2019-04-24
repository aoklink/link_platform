package com.linkfeeling.api.controller.play;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.api.comsumer.GymPlatformServerDelegater;
import com.linkfeeling.api.controller.gym.proxy.APIProxy;
import com.linkfeeling.api.controller.gym.proxy.APIWrapper;
import com.linkfeeling.api.controller.user.UserControllerUtil;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/platform/gym/play/coach")
public class ApiGymPlayCoachController {
    @Autowired
    private GymPlatformServerDelegater gymPlatformServer;

    @Autowired
    private GymAccountServerDelegater gymAccountServer;


    private Map<String, APIProxy> apiProxyMap;

    private Map<String, APIWrapper> apiWrapperMap;

    @PostConstruct
    private void init() {
        apiProxyMap = new HashMap<>();
        apiWrapperMap = new HashMap<>();

        apiWrapperMap.put(ControllerActionContract.OPERATE.ADD,(request, requestJson) -> wrapGymId(ControllerActionContract.OPERATE.ADD,request,requestJson));
        apiProxyMap.put(ControllerActionContract.OPERATE.ADD,(requestJson -> gymPlatformServer.gymPlayCoachAdd(requestJson)));

        apiWrapperMap.put(ControllerActionContract.OPERATE.UPDATE,(request, requestJson) -> wrapGymId(ControllerActionContract.OPERATE.UPDATE,request,requestJson));
        apiProxyMap.put(ControllerActionContract.OPERATE.UPDATE,(requestJson -> gymPlatformServer.gymPlayCoachUpdate(requestJson)));

        apiWrapperMap.put(ControllerActionContract.OPERATE.DELETE,(request, requestJson) -> wrapGymId(ControllerActionContract.OPERATE.DELETE,request,requestJson));
        apiProxyMap.put(ControllerActionContract.OPERATE.DELETE,(requestJson -> gymPlatformServer.gymPlayCoachDelete(requestJson)));

        apiWrapperMap.put(ControllerActionContract.OPERATE.LIST,(request, requestJson) -> wrapGymId(ControllerActionContract.OPERATE.LIST,request,requestJson));
        apiProxyMap.put(ControllerActionContract.OPERATE.LIST,(requestJson -> gymPlatformServer.gymPlayCoachList(requestJson)));
    }

    @RequestMapping(value = {"/*"},produces="application/json;charset=UTF-8")
    public String proxy(HttpServletRequest request, @RequestBody(required = false) String requestJson){
        String path = request.getServletPath();
        String operate = path.substring("/api/platform/gym/play/coach".length());
        if(StringUtils.isEmpty(operate)) return ResponseUtil.newResponseWithDesc(ResponseDesc.INVALID_REQUEST).toString();
        APIWrapper apiWrapper = apiWrapperMap.get(operate);
        if(apiWrapper!=null){
            try {
                requestJson = apiWrapper.wrap(request, requestJson);
            } catch (APIWrapper.NoPermissionException e) {
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NO_PERMISSION,e.getMessage()).toString();
            }
        }
        APIProxy apiProxy = apiProxyMap.get(operate);
        if(apiProxy==null){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,path+" not found").toString();
        }else{
            return apiProxy.proxy(requestJson).toString();
        }
    }

    private String wrapGymId(String operate, HttpServletRequest request,String requestJson) throws APIWrapper.NoPermissionException {
        String userName = request.getUserPrincipal().getName();
        JSONObject userResult = UserControllerUtil.getUserObject(userName,gymAccountServer);
        JSONObject jsonObject;
        if(StringUtils.isEmpty(requestJson)){
            jsonObject = new JSONObject();
        }else{
            jsonObject = JSON.parseObject(requestJson);
            if(jsonObject.containsKey("gym_id") && jsonObject.getLongValue("gym_id")!= userResult.getLongValue("gym_id")){
                throw new APIWrapper.NoPermissionException("no permission to operate gym_id="+jsonObject.getLongValue("gym_id"));
            }
        }
        jsonObject.put("gym_id",userResult.getLongValue("gym_id"));
        return jsonObject.toJSONString();
    }
}
