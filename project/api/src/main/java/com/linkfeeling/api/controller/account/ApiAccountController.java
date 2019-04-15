package com.linkfeeling.api.controller.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.comsumer.GymAccountServer;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zl
 * @date ：Created in 2019/4/4 18:22
 * @description：账号信息
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
@RequestMapping("/api/account")
public class ApiAccountController {
    Logger logger = LoggerFactory.getLogger(ApiAccountController.class);

    @Autowired
    GymAccountServer gymAccountServer;
    /**
     * create by: zl
     * description: 获取用户信息
     * create time:
     *
     * @return
     * @Param: request
     */
    @RequestMapping(ControllerActionContract.OPERATE.GET_ACCOUNT_INFO)
    public String getAccountInfo(@RequestBody String request) {
        String flag = checkParam(request);
        if (flag != null) {
            return flag;
        }
        return gymAccountServer.getAccountInfo(request);
    }

    private String checkParam(String request) {
        if (StringUtils.isBlank(request)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR).toString();
        }
        JSONObject jsonObject = JSON.parseObject(request);
        if (StringUtils.isBlank(jsonObject.getString("uid"))) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR, "uid is must").toString();
        }
        return null;
    }

}
