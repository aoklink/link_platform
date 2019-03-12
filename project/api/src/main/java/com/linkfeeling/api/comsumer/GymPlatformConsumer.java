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

    /**
     * 这一部分接口是给商家管理员用的
     */
    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.GET_ME)
    // {"gym_admin_user_id":1} 此值由api通过权限认证后填入
    Response gymInfoGetMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.UPDATE_ME)
    // {"gym_admin_user_id":1} 此值由api通过权限认证后填入
    Response gymInfoUpdateMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.GET_ME)
    // {"gym_id":1} 此值由api通过权限认证后填入
    Response gymCoachGetMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.UPDATE_ME)
    // {"gym_id":1,"id":2} gym_id值由api通过权限认证后填入
    Response gymCoachUpdateMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.ADD_ME)
    // {"gym_id":1} 此值由api通过权限认证后填入
    Response gymCoachAddMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.DELETE_ME)
    // {"gym_id":1,"id":2} gym_id值由api通过权限认证后填入
    Response gymCoachDeleteMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_coach"+ ControllerActionContract.OPERATE.LIST_ME)
        // {"gym_id":1} 此值由api通过权限认证后填入
    Response gymCoachListMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.GET_ME)
    // {"gym_id":1} 此值由api通过权限认证后填入
    Response gymClassGetMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.UPDATE_ME)
    // {"gym_id":1,"id":2} gym_id值由api通过权限认证后填入
    Response gymClassUpdateMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.ADD_ME)
    // {"gym_id":1} 此值由api通过权限认证后填入
    Response gymClassAddMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.DELETE)
    // {"gym_id":1,"id":2} gym_id值由api通过权限认证后填入
    Response gymClassDeleteMe(@RequestBody String requestJson);

    @PostMapping("/platform/gym_class"+ ControllerActionContract.OPERATE.LIST_ME)
    // {"gym_id":1} gym_id值由api通过权限认证后填入
    Response gymClassListMe(@RequestBody String requestJson);

    /**
     * 这一部分接口是给连锁店老板用的
     * 老板只管基本信息就行，对课程和教练都不关心
     */
    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.LIST_GROUP)
    // {"id_array":[1,2,3]} 此值由api通过权限认证后填入
    Response gymInfoListGroup(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.GET_GROUP)
    // {"id":1} 此值由api通过权限认证后填入,api层需要判断当前登录连锁用户是否有权限查看此店铺
    Response gymInfoGetGroup(@RequestBody String requestJson);

    @PostMapping("/platform/gym_common"+ ControllerActionContract.OPERATE.GET_GROUP)
    // {"id":1} 此值由api通过权限认证后填入,api层需要判断当前登录连锁用户是否有权限查看此店铺
    // 返回的是组合对象，包括
    Response gymCommonGetGroup(@RequestBody String requestJson);

    @PostMapping("/platform/gym_info"+ ControllerActionContract.OPERATE.UPDATE_GROUP)
    // {"id":1} 此值由api通过权限认证后填入,api层需要判断当前登录连锁用户是否有权限查看此店铺
    Response gymInfoUpdateGroup(@RequestBody String requestJson);
}
