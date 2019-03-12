package com.linkfeeling.platform.data.gym.info;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.gym.bean.GymInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/platform/gym_info")
public class GymInfoContoller {
    @Autowired
    private GymInfoComponent gymInfoComponent;

    @PostMapping(ControllerActionContract.OPERATE.ADD)
    public Response add(@RequestBody GymInfo gymInfo){
        try {
            if(gymInfo.getMemberCount()==null){
                gymInfo.setMemberCount(0L);
            }
            return ResponseUtil.newSuccess(gymInfoComponent.toSimpleResponse(gymInfoComponent.save(gymInfo)));
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.DELETE)
    public Response delete(@RequestBody GymInfo gymInfo){
        try {
            Optional<GymInfo> gymInfoInDB = gymInfoComponent.findById(gymInfo.getId());
            if(gymInfoInDB.isPresent()){
                gymInfoComponent.delete(gymInfo);
                return ResponseUtil.newSuccess("delete success");
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response update(@RequestBody GymInfo gymInfo){
        try {
            Optional<GymInfo> gymInfoInDB = gymInfoComponent.findById(gymInfo.getId());
            if(gymInfoInDB.isPresent()){
                return ResponseUtil.newSuccess(gymInfoComponent.toSimpleResponse(gymInfoComponent.save(gymInfo)));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }
    @PostMapping(ControllerActionContract.OPERATE.GET)
    public Response get(@RequestBody GymInfo gymInfo){
        try {
            Optional<GymInfo> gymInfoInDB = gymInfoComponent.findById(gymInfo.getId());
            if(gymInfoInDB.isPresent()){
                return ResponseUtil.newSuccess(gymInfoInDB.get());
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }


}
