package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymCoach;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymCoachRepository;
import com.linkfeeling.platform.util.ArrayListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/gym_coach")
public class GymCoachController {
    @Autowired
    private GymCoachRepository gymCoachRepository;

    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response add(String name, String label, Long gymId){
        GymCoach gymCoach = new GymCoach(name,label,gymId);
        try {
            return ResponseUtil.newSuccess(gymCoachRepository.save(gymCoach));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.DELETE)
    public Response delete(Long id){
        try {
            gymCoachRepository.deleteById(id);
            return ResponseUtil.newSuccess("delete coach success. id["+id+"]");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.UPDATE)
    public Response update(Long id,String name, String label, Long gymId){
        GymCoach gymCoach = new GymCoach(id,name,label,gymId);
        try {
            return ResponseUtil.newSuccess(gymCoachRepository.save(gymCoach));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.GET)
    public Response get(Long id){
        Optional<GymCoach> gymCoach = gymCoachRepository.findById(id);
        if(gymCoach.isPresent()){
            return ResponseUtil.newSuccess(gymCoach.get());
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
        }
    }

    @RequestMapping(ActionContract.OPERATE.LIST)
    public Response list(){
        Iterable<GymCoach> gymCoaches = gymCoachRepository.findAll();
        return ResponseUtil.newSuccess(ArrayListUtil.of(gymCoaches));
    }
}