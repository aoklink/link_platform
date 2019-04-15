package com.linkfeeling.platform.data.link;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.comsumer.AccountServer;
import com.linkfeeling.platform.data.link.bean.GymBraceletBindItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：zl
 * @date ：Created in 2019/4/4 13:18
 * @description：领克平台服务
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
@RequestMapping("/platform/link")
public class LinkPlatformController {
    Logger logger = LoggerFactory.getLogger(LinkPlatformController.class);

    @Autowired
    LinkPlatformComponent linkPlatformComponent;
    @Autowired
    AccountServer accountServer;

    /**
     * create by: zl
     * description: 手环绑定接口
     * create time:
     *
     * @return
     * @Param: request
     */
    @PostMapping(ControllerActionContract.OPERATE.BIND)
    public String bind(@RequestBody String request) {
        // 校验健身房是否录入该手环或者该手环是否已经绑定
        String exist = checkBraceketIsExist(request);
        if (exist != null) {
            return exist;
        }
        String bind = checkBraceketIsBind(request);
        logger.info("查询的用户绑定信息为: {}", bind);
        if (StringUtils.isNotBlank(bind)) {
            JSONObject object = JSON.parseObject(bind);
            String braceletId = object.getString("bracelet_id");
            boolean status = object.getBooleanValue("status");
            if (!status) {
                return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_CHECK_FAILED, braceletId + " is already bind").toString();
            }
        }

        JSONObject reObject = JSON.parseObject(request);
        String phone_num = reObject.getString("phone_num");
        String bracelet_id = reObject.getString("bracelet_id");
        String user_name = reObject.getString("user_name");
        String gym_name = reObject.getString("gym_name");
        //设置绑定时的值
        long bindTime = System.currentTimeMillis();
        JSONObject bindObject = new JSONObject();
        bindObject.put(GymBraceletBindItem.BRACELET_ID, bracelet_id);
        bindObject.put(GymBraceletBindItem.USER_NAME, user_name);
        bindObject.put(GymBraceletBindItem.GYM_NAME, gym_name);
        bindObject.put(GymBraceletBindItem.PHONE_NUM, phone_num);
        bindObject.put(GymBraceletBindItem.STATUS, true);
        bindObject.put(GymBraceletBindItem.BIND_TIME, bindTime);
        try {
            String jsonData = getAccountString(phone_num);
            if (StringUtils.isBlank(jsonData)) {
                JSONObject result = new JSONObject();
                result.put("code", "");
                result.put("msg", "bind fail,user not register");
                return result.toJSONString();
            }
            JSONObject account = JSON.parseObject(jsonData);
            logger.info("查询的账号信息为: {}", account);
            String uid = account.getString("uid");
            if (uid != null && !uid.isEmpty()) {
                bindObject.put(GymBraceletBindItem.UID, uid);
                // 保存每次的绑定时间
                // redisComponent.saveUserBindTime(uid, bindTime);
                // add by zl start 保存绑定信息,now页面用到
                //redisComponent.setBindInfo(uid, bindObject.toJSONString());
                // add by zl start
                //save to mysql
                linkPlatformComponent.saveBindInfoToSQL(gym_name, bracelet_id, user_name, phone_num, uid, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(bindTime)), bindTime, "", "0");
                // TODO 发送消息通知,用户手环已解绑

                JSONObject result = new JSONObject();
                result.put("code", "200");
                result.put("msg", "bind success");
                return result.toJSONString();
            } else {
                logger.info("BraceletController bind uid is empty,phone_num:{}", phone_num);
                JSONObject result = new JSONObject();
                result.put("code", "");
                result.put("msg", "bind fail,user not register");
                return result.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", "500");
            result.put("msg", "bind fail");
            return result.toJSONString();
        }
    }

    /**
     * create by: zl
     * description: 手环解绑接口
     * create time:
     *
     * @return
     * @Param: request
     */
    @PostMapping(ControllerActionContract.OPERATE.UNBIND)
    public String unbind(@RequestBody String request) {
        // 校验健身房是否录入该手环或者该手环是否已经绑定
        String exist = checkBraceketIsExist(request);
        if (exist != null) {
            return exist;
        }
        // 校验手环是否绑定
        String bind = checkBraceketIsBind(request);
        logger.info("解绑手环时查询的用户绑定信息为: {}", bind);
        if (StringUtils.isNotBlank(bind)) {
            JSONObject object = JSON.parseObject(bind);
            boolean status = object.getBooleanValue("status");
            if (status) {
                return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_CHECK_FAILED, "please bind first").toString();
            }
        } else {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_CHECK_FAILED, "please bind first").toString();
        }
        JSONObject object = JSON.parseObject(request);
        String phone_num = object.getString("phone_num");
        String gym_name = object.getString("gym_name");
        String bracelet_id = object.getString("bracelet_id");
        long unBindTime = System.currentTimeMillis();
        try {
            String jsonData = getAccountString(phone_num);
            if (StringUtils.isBlank(jsonData)) {
                JSONObject result = new JSONObject();
                result.put("code", "203");
                result.put("msg", "unbind fail,user not register");
                return result.toJSONString();
            }
            JSONObject account = JSON.parseObject(jsonData);
            String uid = account.getString("uid");
            logger.info("gym_name:{},bracelet_id:{}", gym_name, bracelet_id);
            //redisComponent.setGymBraceletUnbind(gym_name, bracelet_id);

            String userBindValue = linkPlatformComponent.getUserBindBraceletInfo(uid, bracelet_id);
            logger.info("userBindValue:{}", userBindValue);

            if (userBindValue != null) {
                JSONObject userBindObject = JSON.parseObject(userBindValue);
                userBindObject.put(GymBraceletBindItem.STATUS, false);
                userBindObject.put("is_read", false);
                userBindObject.put(GymBraceletBindItem.UNBIND_TIME, unBindTime);
                // 根据绑定时间戳获取缓存中的数据  add by zl
                //String userBindBraceletInfo = redisComponent.getGymData(uid, userBindObject.getString(GymBraceletBindItem.BIND_TIMESTAMP));
//                if (org.apache.commons.lang.StringUtils.isNotBlank(userBindBraceletInfo)) {
//                    JSONObject userObject = JSON.parseObject(userBindBraceletInfo);
//                    String calorie = userObject.getString(GymBraceletBindItem.CALORIE);
//                    userBindObject.put(GymBraceletBindItem.CALORIE, calorie);
//                }

                // 更新Redis中的数据
                //redisComponent.setGymData(uid, userBindObject.getString(GymBraceletBindItem.BIND_TIMESTAMP), userBindObject.toJSONString());

                // add by zl start 更新保存绑定信息,now页面用到
                //redisComponent.setBindInfo(uid, userBindObject.toJSONString());
                // 手环解绑时清除设置目标
                //String redisKey = "gym_target_data_save_" + uid;
                //redisComponent.removeRedisData(redisKey, uid);
                // add by zl start

                // 将数据上传到数据中心备份 add by zl
                //dataCenterServer.uploadToDataCenter(userBindObject.toJSONString());
                // add end by zl

                long bindTime = userBindObject.getLong(GymBraceletBindItem.BIND_TIME);
                String calorie = userBindObject.getString(GymBraceletBindItem.CALORIE);
                //卡路里不为0时,update mysql
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                linkPlatformComponent.updateUnbindInfoToSQL(gym_name, bracelet_id, phone_num, sd.format(new Date(bindTime)), sd.format(new Date(unBindTime)), calorie);

                // TODO 发送消息通知,用户手环已解绑
            } else {
                JSONObject result = new JSONObject();
                result.put("code", "203");
                result.put("msg", "user is not exist");
                return result.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", "500");
            result.put("msg", "unbind fail");
            return result.toJSONString();
        }

        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "unbind success");
        return result.toJSONString();
    }

    /**
     * create by: zl
     * description: 获取账号信息
     * create time:
     *
     * @return
     * @Param: phone_num
     */
    private String getAccountString(String phone_num) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone_num", phone_num);
        jsonObject.put("user_type", "trainee");
        return accountServer.get(jsonObject.toJSONString());
    }

    /**
     * create by: zl
     * description: 校验健身房是否录入该手环和该手环是否已经绑定
     * create time:
     *
     * @return
     * @Param: request
     */
    private String checkBraceketIsExist(String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String gymName = JSON.parseObject(request).getString("gym_name");
        String braceletId = jsonObject.getString("bracelet_id");
        boolean flag = linkPlatformComponent.isBraceletBeyondGym(gymName, braceletId);
        if (!flag) {
            return ResponseUtil.newResponseWithDesc(ResponseDesc.PARAM_CHECK_FAILED, braceletId + " not beyond " + gymName).toString();
        }
        return null;
    }

    /**
     * create by: zl
     * description: 校验健身房是否录入该手环和该手环是否已经绑定
     * create time:
     *
     * @return
     * @Param: request
     */
    private String checkBraceketIsBind(String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String gymName = JSON.parseObject(request).getString("gym_name");
        String braceletId = jsonObject.getString("bracelet_id");
        String bind = linkPlatformComponent.verifyBraceletidBind(gymName, braceletId);
        return bind;
    }


    /**
     * create by: zl
     * description: 获取会员列表
     * create time:
     *
     * @return
     * @Param: request
     */
    @PostMapping(ControllerActionContract.OPERATE.MEMBERS)
    public String members(@RequestBody String gymName) {
        List<JSONObject> members = linkPlatformComponent.getMembers(gymName);
        return responseJson("200", "ok", members);
    }

    public String responseJson(String code, String msg, Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", object);
        return jsonObject.toJSONString();
    }
}
