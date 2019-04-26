package com.linkfeeling.platform.data.play.coach;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayCoach;
import com.linkfeeling.platform.data.play.bean.GymPlayCoachWithGym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/platform/gym/play/coach")
public class PlayCoachController {

    @Autowired
    private PlayCoachRichComponent playCoachRichComponent;

    @Autowired
    private GymPlayCoachWithGymComponent gymPlayCoachWithGymComponent;

    @Autowired
    private PlayCoachComponent playCoachComponent;

    @PostMapping(ControllerActionContract.OPERATE.GET)
    public Response get(@RequestBody GymPlayCoach gymPlayCoach){
        try {
            Optional<GymPlayCoach> gymPlayCoachOptional = playCoachComponent.findByPhoneNum(gymPlayCoach.getPhoneNum());
            if(gymPlayCoachOptional.isPresent()){
                return ResponseUtil.newSuccess(gymPlayCoachOptional.get());
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,"not found by phone:"+gymPlayCoach.getPhoneNum());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.DO_BIND)
    public Response bind(@RequestBody GymPlayCoachWithGym gymPlayCoachWithGym){
        try {
            if(gymPlayCoachWithGymComponent.checkBind(gymPlayCoachWithGym)){
                return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"");
            }else{
                return ResponseUtil.newSuccess( gymPlayCoachWithGymComponent.save(gymPlayCoachWithGym));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.DO_UNBIND)
    public Response unbind(@RequestBody GymPlayCoachWithGym gymPlayCoachWithGym){
        try {
            gymPlayCoachWithGymComponent.delete(gymPlayCoachWithGym);
            return ResponseUtil.newSuccess("OK");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.LIST)
    public Response list(@RequestBody GymPlayCoachWithGym gymPlayCoachWithGym){
        try {
            return ResponseUtil.newSuccess(playCoachRichComponent.findAllCoachByGymId(gymPlayCoachWithGym.getGymId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.LIST_MEMBER)
    public Response listMember(@RequestBody GymPlayCoachWithGym gymPlayCoachWithGym){
        try {
            return ResponseUtil.newSuccess(playCoachRichComponent.findAllMemberByCoachUid(gymPlayCoachWithGym.getCoachUid()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
}
