package com.linkfeeling.api.controller.link;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.common.Common;
import com.linkfeeling.api.comsumer.GymPlatformServer;
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

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：zl
 * @date ：Created in 2019/4/3 20:00
 * @description：领客后台服务
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
@RequestMapping("/api/platform")
public class ApiLinkController {
    Logger logger = LoggerFactory.getLogger(ApiLinkController.class);

    @Autowired
    GymPlatformServer gymPlatformServer;

    /**
     * create by: zl
     * description: 手环绑定接口
     * create time:
     *
     * @return
     * @Param: request
     */
    @RequestMapping(ControllerActionContract.OPERATE.BIND)
    public String bind(@RequestBody String request) {
        logger.info("bind, request: {}", request);
        String rusult = checkParam(request);
        if (rusult != null) {
            return rusult;
        }
        return gymPlatformServer.bind(request);
    }

    /**
     * create by: zl
     * description: 手环解绑接口
     * create time:
     *
     * @return
     * @Param: request
     */
    @RequestMapping(ControllerActionContract.OPERATE.UNBIND)
    public String unbind(@RequestBody String request) {

        logger.info("unbind, request: {}", request);
        String rusult = check(request);
        if (rusult != null) {
            return rusult;
        }
        return gymPlatformServer.unbind(request);
    }

    /**
     * create by: zl
     * description: 获取会员列表接口
     * create time:
     *
     * @return
     * @Param: request
     */
    @RequestMapping(ControllerActionContract.OPERATE.MEMBERS)
    public String members(HttpServletRequest request) {
        try {
            logger.info("members, request: {}", request);
            String jsessionid = (String) request.getSession().getAttribute("JSESSIONID");
            logger.info("getMembers,jsessionid:{}", jsessionid);

            JSONObject requestObject = Common.receivePost(request);
            String gym_name = requestObject.getString("gym_name");
            if (org.apache.commons.lang.StringUtils.isBlank(gym_name)) {
                ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR, "gym_name is must").toString();
            }
            return gymPlatformServer.getMembers(gym_name);
        } catch (Exception e) {
            logger.info("getMembers,Exception:{}", e);
            JSONObject object = new JSONObject();
            object.put("code", "500");
            object.put("msg", "server error:" + e.getMessage());
            return object.toJSONString();
        }
    }

    private String check(String request) {
        if (StringUtils.isBlank(request)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR).toString();
        }
        JSONObject object = JSON.parseObject(request);
        String bracelet_id = object.getString("bracelet_id");
        String gym_name = object.getString("gym_name");
        String phone_num = object.getString("phone_num");
        if (StringUtils.isBlank(bracelet_id) || StringUtils.isBlank(gym_name) || StringUtils.isBlank(phone_num)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR, "gym_name or bracelet_id or phone_num is empty").toString();
        }
        return null;
    }

    /**
     * create by: zl
     * description: 参数校验
     * create time:
     *
     * @return
     * @Param: request
     */
    private String checkParam(String request) {
        if (StringUtils.isBlank(request)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR).toString();
        }
        JSONObject jsonObject = JSON.parseObject(request);
        String gymName = jsonObject.getString("gym_name");
        String phoneNum = jsonObject.getString("phone_num");
        if (StringUtils.isBlank(gymName) || StringUtils.isBlank(phoneNum)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR, "gym_name or phone_num is empty").toString();
        }
        String braceletId = jsonObject.getString("bracelet_id");
        String bindTime = jsonObject.getString("bind_time");
        if (StringUtils.isBlank(braceletId) || StringUtils.isBlank(bindTime)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR, "bracelet_id or bind_time is empty").toString();
        }
        String userName = jsonObject.getString("user_name");
        if (StringUtils.isBlank(braceletId) || StringUtils.isBlank(userName)) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_ERROR, "user_name is empty").toString();
        }
        return null;
    }
}
