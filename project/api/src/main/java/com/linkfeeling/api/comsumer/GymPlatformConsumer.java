package com.linkfeeling.api.comsumer;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface GymPlatformConsumer {

    /**
     * 这一部分接口是给系统管理员使用的
     */

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.GET)
    // {"id":1}
    Response gymInfoGet(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.UPDATE)
    // {"id":1}
    Response gymInfoUpdate(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.ADD)
    // {"id":1}
    Response gymInfoAdd(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.DELETE)
    // {"id":1}
    Response gymInfoDelete(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.LIST_ALL)
    // 不需要参数
    Response gymInfoListAll(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.GET)
    // {"id":1}
    Response gymCoachGet(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.UPDATE)
    // {"id":1}
    Response gymCoachUpdate(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.ADD)
    Response gymCoachAdd(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.DELETE)
    // {"id":1}
    Response gymCoachDelete(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.LIST)
    // {"gym_id":1}
    Response gymCoachListByGymId(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.GET)
    // {"id":1}
    Response gymClassGet(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.UPDATE)
    // {"id":1}
    Response gymClassUpdate(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.ADD)
    Response gymClassAdd(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.DELETE)
    // {"id":1}
    Response gymClassDelete(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.LIST)
    // {"gym_id":1}
    Response gymClassListByGymId(@RequestBody String requestJson);
}
