package com.linkfeeling.api.comsumer;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class GymPlatformServerDelegater {
    @Autowired
    private GymPlatformServer gymPlatformServer;

    public Response gymInfoGet(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymInfoGet(requestJson));
    }

    public Response gymInfoUpdate(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymInfoUpdate(requestJson));
    }

    public Response gymInfoAdd(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymInfoAdd(requestJson));
    }

    public Response gymInfoDelete(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymInfoDelete(requestJson));
    }

    public Response gymInfoListAll() {
        return ResponseUtil.parse(gymPlatformServer.gymInfoListAll());
    }

    public Response gymCoachGet(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymCoachGet(requestJson));
    }

    public Response gymCoachUpdate(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymCoachUpdate(requestJson));
    }

    public Response gymCoachAdd(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymCoachAdd(requestJson));
    }

    public Response gymCoachDelete(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymCoachDelete(requestJson));
    }

    public Response gymCoachListByGymId(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymCoachListByGymId(requestJson));
    }

    public Response gymClassGet(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymClassGet(requestJson));
    }

    public Response gymClassUpdate(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymClassUpdate(requestJson));
    }

    public Response gymClassAdd(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymClassAdd(requestJson));
    }

    public Response gymClassDelete(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymClassDelete(requestJson));
    }

    public Response gymClassListByGymId(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymClassListByGymId(requestJson));
    }

    public Response gymPlayMemberListByGymName(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymPlayMemberList(requestJson));
    }

    public Response gymPlayMemberGet(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymPlayMemberGet(requestJson));
    }

    public Response gymPlayMemberTrans(String requestJson) {
        return ResponseUtil.parse(gymPlatformServer.gymPlayMemberTrans(requestJson));
    }

    public Response gymPlayCoachGet(String requestJson){
        return ResponseUtil.parse(gymPlatformServer.gymPlayCoachGet(requestJson));
    }
    public Response gymPlayCoachBind(String requestJson){
        return ResponseUtil.parse(gymPlatformServer.gymPlayCoachBind(requestJson));
    }
    public Response gymPlayCoachUnBind(String requestJson){
        return ResponseUtil.parse(gymPlatformServer.gymPlayCoachUnBind(requestJson));
    }
    public Response gymPlayCoachList(String requestJson){
        return ResponseUtil.parse(gymPlatformServer.gymPlayCoachList(requestJson));
    }

    public Response gymPlayCoachListMember(String requestJson){
        return ResponseUtil.parse(gymPlatformServer.gymPlayCoachListMember(requestJson));
    }

}
