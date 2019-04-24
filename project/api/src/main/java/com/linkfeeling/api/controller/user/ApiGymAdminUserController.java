package com.linkfeeling.api.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.auth.user.manager.IUserAuthority;
import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.api.controller.gym.proxy.APIWrapper;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/account/gym_admin_user")
public class ApiGymAdminUserController {
    @Autowired
    private GymAccountServerDelegater gymAccountServer;

    @PostMapping(value={ControllerActionContract.OPERATE.ADD},produces="application/json;charset=UTF-8")
    public String gymAdminUserAdd(HttpServletRequest request, @RequestBody String requestJson) {
        JSONObject user = getUserObject(request);
        boolean isSystem = isSystemUser(user);
        JSONObject requestObj = JSON.parseObject(requestJson);
        if(isSystem){
            String permission_array = requestObj.getString("permission_array");
            if(StringUtils.isEmpty(permission_array)){
                requestObj.put("permission_array","[\"admin\"]");
            }
        }else{
            String permission_array = requestObj.getString("permission_array");
            if(StringUtils.isEmpty(permission_array) ){
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NO_PERMISSION,"permission_array is need").toString();
            }

            if(permission_array.contains("admin")){
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NO_PERMISSION,"permission cannot be admin").toString();
            }
        }
        return gymAccountServer.gymAdminUserAdd(requestObj.toJSONString()).toString();
    }
    @PostMapping(value={ControllerActionContract.OPERATE.UPDATE},produces="application/json;charset=UTF-8")
    public String gymAdminUserUpdate(HttpServletRequest request,@RequestBody String requestJson) {
        JSONObject user = getUserObject(request);
        boolean isSystem = isSystemUser(user);
        JSONObject requestObj = JSON.parseObject(requestJson);
        if(isSystem){
            String permission_array = requestObj.getString("permission_array");
            if(StringUtils.isEmpty(permission_array)){
                requestObj.put("permission_array","[\"admin\"]");
            }
        }else{
            String permission_array = requestObj.getString("permission_array");
            if(StringUtils.isEmpty(permission_array) || permission_array.contains("admin")){
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NO_PERMISSION,"permission_array is need").toString();
            }
        }
        return gymAccountServer.gymAdminUserUpdate(requestJson).toString();
    }
    @PostMapping(value={ControllerActionContract.OPERATE.DELETE},produces="application/json;charset=UTF-8")
    public String gymAdminUserDelete(@RequestBody String requestJson, HttpServletResponse httpServletResponse) {
        return gymAccountServer.gymAdminUserDelete(requestJson).toString();
    }

    @PostMapping(value={ControllerActionContract.OPERATE.GET_BIND},produces="application/json;charset=UTF-8")
    public String gymAdminUserBind(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserGetByGymId(requestJson).toString();
    }

    @PostMapping(value={ControllerActionContract.OPERATE.LIST},produces="application/json;charset=UTF-8")
    public String gymAdminUserList(HttpServletRequest request) {
        try {
            return gymAccountServer.gymAdminUserList(wrapGymId(request,"")).toString();
        } catch (APIWrapper.NoPermissionException e) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NO_PERMISSION,"").toString();
        } catch (Exception e){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NO_PERMISSION,e.getMessage()).toString();
        }
    }

    private String wrapGymId(HttpServletRequest request,String requestJson) throws Exception {
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
        if(userResult.containsKey("gym_id")){
            jsonObject.put("gym_id",userResult.getLongValue("gym_id"));
        }else{
            throw new Exception("no permission");
        }
        return jsonObject.toJSONString();
    }

    private JSONObject getUserObject(HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        JSONObject userResult = UserControllerUtil.getRichUserObject(userName,gymAccountServer);
        return userResult;
    }

    private boolean isSystemUser(JSONObject userObj){
        JSONArray jsonArray = userObj.getJSONArray("authorities");
        for (int i = 0; i < jsonArray.size(); i++) {
            if(IUserAuthority.SYSTEM.equals(jsonArray.getString(i))){
                return true;
            }
        }
        return false;
    }
}
