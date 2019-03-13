package com.linkfeeling.platform.data.gym.coach;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.gym.bean.GymCoach;
import com.linkfeeling.platform.data.gym.bean.GymInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/platform/gym_coach")
public class GymCoachContoller {
    @Autowired
    private GymCoachComponent gymCoachComponent;

    @PostMapping(ControllerActionContract.OPERATE.ADD)
    public Response add(@RequestBody GymCoach gymCoach){
        try {
            return ResponseUtil.newSuccess(gymCoachComponent.toSimpleResponse(gymCoachComponent.save(gymCoach)));
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.DELETE)
    public Response delete(@RequestBody GymCoach gymCoach){
        try {
            Optional<GymCoach> gymCoachOptional = gymCoachComponent.findById(gymCoach.getId());
            if(gymCoachOptional.isPresent()){
                gymCoachComponent.delete(gymCoachOptional.get());
                return ResponseUtil.newSuccess("delete success");
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response update(@RequestBody GymCoach gymCoach){
        try {
            Optional<GymCoach> gymCoachInDB = gymCoachComponent.findById(gymCoach.getId());
            if(gymCoachInDB.isPresent()){
                return ResponseUtil.newSuccess(gymCoachComponent.toSimpleResponse(gymCoachComponent.save(gymCoach)));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }
    @PostMapping(ControllerActionContract.OPERATE.GET)
    public Response get(@RequestBody GymCoach gymCoach){
        try {
            Optional<GymCoach> gymCoachInDB = gymCoachComponent.findById(gymCoach.getId());
            if(gymCoachInDB.isPresent()){
                return ResponseUtil.newSuccess(gymCoachInDB.get());
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.LIST)
    public Response list(@RequestBody GymCoach gymCoach){
        try {
            List<GymCoach> gymCoaches = gymCoachComponent.findAllByGymId(gymCoach.getGymId());
            return ResponseUtil.newSuccess(gymCoaches);
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }
}
