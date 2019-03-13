package com.linkfeeling.api.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.common.interactive.util.ResponseUtil;

public class UserControllerUtil {
    public static String buildUserFindArg(String nameOrPhone){
        return "{\"phone\":\""+nameOrPhone+"\"}";
    }

    public static JSONObject getUserObject(String nameOrPhone, GymAccountServerDelegater gymAccountServer){
        JSONObject jsonObject = JSONObject.parseObject(ResponseUtil.getDataString(gymAccountServer.userFind(UserControllerUtil.buildUserFindArg(nameOrPhone))));
        return jsonObject.getJSONObject("user");
    }
}
