package com.linkfeeling.account.data.common.user;

import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account/common")
@RestController
public class CommonUserController {

    @Autowired
    private CommonUserComponent commonUserComponent;

    @PostMapping(ControllerActionContract.OPERATE.EXIST)
        // {"phone":"13012341234"}
    public Response userExist(@RequestBody String requestJson){
        JSONObject jsonObject = JSONObject.parseObject(requestJson);
        String phone = jsonObject.getString("phone");
        if(commonUserComponent.exist(phone)){
            return ResponseUtil.newSuccess("OK");
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,"not exist");
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.FIND)
        // {"phone":"13012341234"}
    Response userFind(@RequestBody String requestJson){
        JSONObject jsonObject = JSONObject.parseObject(requestJson);
        String phone = jsonObject.getString("phone");
        return commonUserComponent.findUser(phone);
    }
}
