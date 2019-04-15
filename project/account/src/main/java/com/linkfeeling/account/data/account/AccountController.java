package com.linkfeeling.account.data.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.common.controller.ControllerActionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zl
 * @date ：Created in 2019/4/4 14:11
 * @description：账号信息
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountComponent accountComponent;

    @PostMapping(ControllerActionContract.OPERATE.GET_ACCOUNT)
    public String getAccount(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String phone_num = jsonObject.getString("phone_num");
        String user_type = jsonObject.getString("user_type");
        String account = accountComponent.getUserAccount(phone_num, user_type);
        return account;
    }

    @PostMapping(ControllerActionContract.OPERATE.GET_ACCOUNT_INFO)
    public String getAccountInfo(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String uid = jsonObject.getString("uid");
        String account = accountComponent.getUserAccountInfo(uid);
        return responseJson("200", "ok", JSON.parseObject(account));
    }

    public String responseJson(String code, String msg, Object object) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", object);
        return result.toJSONString();
    }
}
