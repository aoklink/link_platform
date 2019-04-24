package com.linkfeeling.platform.data.play.member;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platform/gym/play/member")
public class PlayMemberController {

    @Autowired
    private PlayMemberComponent playMemberComponent;

    @PostMapping(ControllerActionContract.OPERATE.LIST)
    public Response list(@RequestBody GymPlayMember gymPlayMember){
        return ResponseUtil.newSuccess(playMemberComponent.listByGymName(gymPlayMember.getGymName()));
    }
}
