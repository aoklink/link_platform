package com.linkfeeling.api.comsumer;

import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.common.interactive.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymAccountServerDelegater {
    @Autowired
    private GymAccountServer gymAccountServer;

    public Response userExist(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.userExist(requestJson),Response.class);
    }

    public Response userFind(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.userFind(requestJson),Response.class);
    }

    public Response systemUserAdd(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.systemUserAdd(requestJson),Response.class);
    }

    public Response gymAdminUserAdd(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.gymAdminUserAdd(requestJson),Response.class);
    }

    public Response systemUserUpdate(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.systemUserUpdate(requestJson),Response.class);
    }

    public Response gymAdminUserUpdate(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.gymAdminUserUpdate(requestJson),Response.class);
    }

    public Response systemUserDelete(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.systemUserDelete(requestJson),Response.class);
    }

    public Response gymAdminUserDelete(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.gymAdminUserDelete(requestJson),Response.class);
    }

    public Response gymAdminUserGetByGymId(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.gymAdminUserGetByGymId(requestJson),Response.class);
    }

    public Response gymAdminUserList(String requestJson) {
        return JSONObject.parseObject(gymAccountServer.gymAdminUserList(requestJson),Response.class);
    }
}
