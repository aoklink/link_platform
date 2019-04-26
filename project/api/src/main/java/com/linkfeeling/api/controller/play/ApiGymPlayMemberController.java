package com.linkfeeling.api.controller.play;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.api.comsumer.GymPlatformServerDelegater;
import com.linkfeeling.api.controller.gym.proxy.APIWrapper;
import com.linkfeeling.api.controller.user.UserControllerUtil;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/platform/gym/play/member")
public class ApiGymPlayMemberController {
    @Autowired
    private GymPlatformServerDelegater gymPlatformServer;

    @Autowired
    private GymAccountServerDelegater gymAccountServer;

    @RequestMapping(value = {ControllerActionContract.OPERATE.LIST},produces="application/json;charset=UTF-8")
    public String list(HttpServletRequest request, @RequestBody(required = false) String requestJson){
        try {
            Long gymId = getUserGymId(request,requestJson);
            String gymName = getGymName(gymId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gym_name",gymName);
            return gymPlatformServer.gymPlayMemberListByGymName(jsonObject.toJSONString()).toString();
        } catch (APIWrapper.NoPermissionException e) {
            return ResponseUtil.newException(e).toString();
        }
    }

    @RequestMapping(value = {ControllerActionContract.OPERATE.GET},produces="application/json;charset=UTF-8")
    public String get(HttpServletRequest request, @RequestBody(required = true) String requestJson){
        return gymPlatformServer.gymPlayMemberGet(requestJson).toString();
    }

    @RequestMapping(value = {ControllerActionContract.OPERATE.TRANS},produces="application/json;charset=UTF-8")
    public String trans(HttpServletRequest request, @RequestBody(required = true) String requestJson){
        try {
            return gymPlatformServer.gymPlayMemberTrans(wrapGymId(request,requestJson)).toString();
        } catch (APIWrapper.NoPermissionException e) {
            return ResponseUtil.newException(e).toString();
        }
    }

    private Long getUserGymId(HttpServletRequest request, String requestJson)throws APIWrapper.NoPermissionException {
        String userName = request.getUserPrincipal().getName();
        JSONObject userResult = UserControllerUtil.getUserObject(userName,gymAccountServer);
        JSONObject jsonObject;
        if(StringUtils.isEmpty(requestJson)){
            jsonObject = new JSONObject();
        }else{
            jsonObject = JSON.parseObject(requestJson);
            if(jsonObject.containsKey("id") && jsonObject.getLongValue("id")!= userResult.getLongValue("gym_id")){
                throw new APIWrapper.NoPermissionException("no permission to operate id="+jsonObject.getLongValue("id"));
            }
        }
        return userResult.getLongValue("gym_id");
    }

    private String getGymName(Long gymId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",gymId);
        Response response = gymPlatformServer.gymInfoGet(jsonObject.toJSONString());
        JSONObject jsonObjectResult = (JSONObject) response.getData();
        return jsonObjectResult.getString("name");
    }

    private String wrapGymId(HttpServletRequest request,String requestJson) throws APIWrapper.NoPermissionException {
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
