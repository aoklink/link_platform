package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymClass;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
import com.linkfeeling.platform.repo.gym.GymClassRepository;
import com.linkfeeling.platform.util.ArrayListUtil;
import com.linkfeeling.platform.util.BeanWriteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/gym_class")
public class GymClassController {
    @Autowired
    private GymClassRepository gymClassRepository;
    @Autowired
    private GymAdminUserRepository gymAdminUserRepository;

    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response add(String title, String price_info, String content, Long gym_id){
        GymClass gymClass = new GymClass(title,price_info,content,gym_id,0);
        try {
            return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymClassRepository.save(gymClass)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.ADD_ME)
    public Response addMe(HttpServletRequest request,String title, String price_info, String content){
        return GymControllerUtil.onMeRequest(request,gymAdminUserRepository,gymId -> add(title,price_info,content,gymId));
    }

    @RequestMapping(ActionContract.OPERATE.DELETE)
    public Response delete(Long id){
        try {
            gymClassRepository.deleteById(id);
            return ResponseUtil.newSuccess("delete class success. id["+id+"]");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.DELETE_ME)
    public Response deleteMe(HttpServletRequest request,Long id){
        return GymControllerUtil.onMeRequest(request,gymAdminUserRepository,gymId ->
                gymClassRepository.findByGymIdAndId(gymId,id).isPresent()?
                        delete(id):
                        GymControllerUtil.notExistResponse("class",gymId,id)
        );


    }

    @RequestMapping(ActionContract.OPERATE.UPDATE)
    public Response update(Long id,String title, String price_info, String content, Long gym_id,Integer state){
        GymClass gymClass = new GymClass(id,title,price_info,content,gym_id,state);
        try {
            gymClass = BeanWriteUtil.write(GymClass.class,gymClassRepository.findById(id).get(),gymClass);
            return ResponseUtil.newSuccess(gymClassRepository.save(gymClass));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.UPDATE_ME)
    public Response updateMe(HttpServletRequest request,Long id,String title, String price_info, String content,Integer state){
        return GymControllerUtil.onMeRequest(request,gymAdminUserRepository,gymId ->
                gymClassRepository.findByGymIdAndId(gymId,id).isPresent()?
                        update(id,title,price_info,content,gymId,state):
                        GymControllerUtil.notExistResponse("class",gymId,id));

    }

    @RequestMapping(ActionContract.OPERATE.GET)
    public Response get(Long id){
        Optional<GymClass> gymClass = gymClassRepository.findById(id);
        if(gymClass.isPresent()){
            return ResponseUtil.newSuccess(gymClass.get());
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
        }
    }

    @RequestMapping(ActionContract.OPERATE.GET_ME)
    public Response getMe(HttpServletRequest request,Long id){
       return GymControllerUtil.onMeRequest(request,gymAdminUserRepository,
               gymId ->
                       gymClassRepository.findByGymIdAndId(gymId,id).isPresent()?
                               get(id):
                               GymControllerUtil.notExistResponse("class",gymId,id)
               );
    }

    @RequestMapping(ActionContract.OPERATE.LIST_ALL)
    public Response listAll(){
        Iterable<GymClass> gymCoaches = gymClassRepository.findAll();
        return ResponseUtil.newSuccess(ArrayListUtil.of(gymCoaches));
    }

    @RequestMapping(ActionContract.OPERATE.LIST_ME)
    public Response listMe(HttpServletRequest servletRequest){
        return GymControllerUtil.onMeRequest(servletRequest, gymAdminUserRepository, gymId -> list(gymId));
    }

    @RequestMapping(ActionContract.OPERATE.LIST)
    public Response list(Long gym_id){
        Iterable<GymClass> gymCoaches = gymClassRepository.findAllByGymId(gym_id);
        return ResponseUtil.newSuccess(ArrayListUtil.of(gymCoaches));
    }
}
