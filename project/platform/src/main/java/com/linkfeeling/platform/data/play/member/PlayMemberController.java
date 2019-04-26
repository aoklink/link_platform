package com.linkfeeling.platform.data.play.member;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayMember;
import com.linkfeeling.platform.data.play.bean.request.GymMemberTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/platform/gym/play/member")
public class PlayMemberController {

    @Autowired
    private PlayMemberComponent playMemberComponent;

    @Autowired
    private GymPlayMemberWithCoachComponent gymPlayMemberWithCoachComponent;

    @PostMapping(ControllerActionContract.OPERATE.LIST)
    public Response list(@RequestBody GymPlayMember gymPlayMember){
        return ResponseUtil.newSuccess(playMemberComponent.listByGymName(gymPlayMember.getGymName()));
    }

    @PostMapping(ControllerActionContract.OPERATE.GET)
    public Response get(@RequestBody GymPlayMember gymPlayMember){
        Optional<GymPlayMember> gymPlayMemberOptional = playMemberComponent.findByPhoneNum(gymPlayMember.getPhoneNum());
        if(gymPlayMemberOptional.isPresent()){
            return ResponseUtil.newSuccess(gymPlayMemberOptional.get());
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,"not found by:"+gymPlayMember.getPhoneNum());
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.TRANS)
    public Response trans(@RequestBody GymMemberTrans gymMemberTrans){
        try {
            gymPlayMemberWithCoachComponent.trans(gymMemberTrans);
            return ResponseUtil.newSuccess("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR,e.getMessage());
        }
    }
}
