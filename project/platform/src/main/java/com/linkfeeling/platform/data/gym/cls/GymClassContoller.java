package com.linkfeeling.platform.data.gym.cls;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.gym.bean.GymClass;
import com.linkfeeling.platform.data.gym.coach.GymCoachComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/platform/gym_class")
public class GymClassContoller {
    @Autowired
    private GymClassComponent gymClassComponent;

    @PostMapping(ControllerActionContract.OPERATE.ADD)
    public Response add(@RequestBody GymClass gymClass){
        try {
            return ResponseUtil.newSuccess(gymClassComponent.toSimpleResponse(gymClassComponent.save(gymClass)));
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.DELETE)
    public Response delete(@RequestBody GymClass gymClass){
        try {
            Optional<GymClass> gymCoachOptional = gymClassComponent.findById(gymClass.getId());
            if(gymCoachOptional.isPresent()){
                gymClassComponent.delete(gymCoachOptional.get());
                return ResponseUtil.newSuccess("delete success");
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response update(@RequestBody GymClass gymClass){
        try {
            Optional<GymClass> gymCoachInDB = gymClassComponent.findById(gymClass.getId());
            if(gymCoachInDB.isPresent()){
                return ResponseUtil.newSuccess(gymClassComponent.toSimpleResponse(gymClassComponent.save(gymClass)));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }
    @PostMapping(ControllerActionContract.OPERATE.GET)
    public Response get(@RequestBody GymClass gymClass){
        try {
            Optional<GymClass> gymCoachInDB = gymClassComponent.findById(gymClass.getId());
            if(gymCoachInDB.isPresent()){
                return ResponseUtil.newSuccess(gymCoachInDB.get());
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }
}
