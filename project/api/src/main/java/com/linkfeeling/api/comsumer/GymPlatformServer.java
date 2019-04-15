package com.linkfeeling.api.comsumer;

import com.linkfeeling.common.controller.ControllerActionContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Service
@FeignClient(value = "link-platform-p-online")
public interface GymPlatformServer {

    /**
     * 这一部分接口是给系统管理员使用的
     */

    @PostMapping(value = {"/platform/gym_info"+ ControllerActionContract.OPERATE.GET},consumes = "application/json")
    // {"id":1}
    String gymInfoGet(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_info"+ ControllerActionContract.OPERATE.UPDATE},consumes = "application/json")
    // {"id":1}
    String gymInfoUpdate(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_info"+ ControllerActionContract.OPERATE.ADD},consumes = "application/json")
    // {"id":1}
    String gymInfoAdd(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_info"+ ControllerActionContract.OPERATE.DELETE},consumes = "application/json")
    // {"id":1}
    String gymInfoDelete(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_info"+ ControllerActionContract.OPERATE.LIST_ALL},consumes = "application/json")
    // 不需要参数
    String gymInfoListAll();

    @PostMapping(value = {"/platform/gym_coach"+ ControllerActionContract.OPERATE.GET},consumes = "application/json")
    // {"id":1}
    String gymCoachGet(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_coach"+ ControllerActionContract.OPERATE.UPDATE},consumes = "application/json")
    // {"id":1}
    String gymCoachUpdate(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_coach"+ ControllerActionContract.OPERATE.ADD},consumes = "application/json")
    String gymCoachAdd(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_coach"+ ControllerActionContract.OPERATE.DELETE},consumes = "application/json")
    // {"id":1}
    String gymCoachDelete(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_coach"+ ControllerActionContract.OPERATE.LIST},consumes = "application/json")
    // {"gym_id":1}
    String gymCoachListByGymId(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_class"+ ControllerActionContract.OPERATE.GET},consumes = "application/json")
    // {"id":1}
    String gymClassGet(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_class"+ ControllerActionContract.OPERATE.UPDATE},consumes = "application/json")
    // {"id":1}
    String gymClassUpdate(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_class"+ ControllerActionContract.OPERATE.ADD},consumes = "application/json")
    String gymClassAdd(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_class"+ ControllerActionContract.OPERATE.DELETE},consumes = "application/json")
    // {"id":1}
    String gymClassDelete(@RequestBody String requestJson);

    @PostMapping(value = {"/platform/gym_class"+ ControllerActionContract.OPERATE.LIST},consumes = "application/json")
    // {"gym_id":1}
    String gymClassListByGymId(@RequestBody String requestJson);


    // add by zl 绑定手环接口
    @PostMapping(value = {"/platform/link" + ControllerActionContract.OPERATE.BIND}, consumes = "application/json")
    String bind(@RequestBody String request);
    @PostMapping(value = {"/platform/link" + ControllerActionContract.OPERATE.UNBIND}, consumes = "application/json")
    String unbind(@RequestBody String request);
    @PostMapping(value = {"/platform/link" + ControllerActionContract.OPERATE.MEMBERS}, consumes = "application/json")
    String getMembers(@RequestBody String gym_name);
}
