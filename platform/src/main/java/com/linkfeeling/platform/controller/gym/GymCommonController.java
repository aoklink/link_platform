package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.interactive.response.gym.GymCommonInfo;
import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymClass;
import com.linkfeeling.platform.bean.jpa.gym.GymCoach;
import com.linkfeeling.platform.bean.jpa.gym.GymInfo;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
import com.linkfeeling.platform.repo.gym.GymClassRepository;
import com.linkfeeling.platform.repo.gym.GymCoachRepository;
import com.linkfeeling.platform.repo.gym.GymInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gym_common")
public class GymCommonController {
    @Autowired
    private GymAdminUserRepository gymAdminUserRepository;
    @Autowired
    private GymCoachRepository gymCoachRepository;
    @Autowired
    private GymClassRepository gymClassRepository;
    @Autowired
    private GymInfoRepository gymInfoRepository;

    @RequestMapping(ActionContract.OPERATE.DELETE)
    @Transactional
    public Response delete(Long id){
        try {
            gymInfoRepository.deleteById(id);
            gymClassRepository.deleteByGymId(id);
            gymCoachRepository.deleteByGymId(id);
            gymAdminUserRepository.deleteByGymId(id);
            return ResponseUtil.newSuccess("delete all gym success. [id:"+id+"]");
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.GET)
    @Transactional
    public Response get(Long id){
        try {
            Optional<GymInfo> gymInfo = gymInfoRepository.findById(id);
            if(gymInfo.isPresent()){
                List<GymClass> gymClassList =  gymClassRepository.findAllByGymId(id);
                Optional<GymAdminUser> gymAdminUser =  gymAdminUserRepository.findByGymId(id);
                List<GymCoach> gymCoachList =  gymCoachRepository.findAllByGymId(id);


                GymCommonInfo gymCommonInfo = new GymCommonInfo(gymInfo.get(),
                        InteractiveBeanUtil.from(gymAdminUser.get()),
                        gymClassList.stream()
                                .map(gymClass -> InteractiveBeanUtil.from(gymClass))
                                .collect(Collectors.toList()),
                        gymCoachList
                        );
                return ResponseUtil.newSuccess(gymCommonInfo);
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,"gym info not exist. [id:"+id+"]");
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.GET_ME)
    public Response getGymInfo(HttpServletRequest servletRequest){
        return GymControllerUtil.onMeRequest(servletRequest,gymAdminUserRepository,gymId -> get(gymId));
    }


}
