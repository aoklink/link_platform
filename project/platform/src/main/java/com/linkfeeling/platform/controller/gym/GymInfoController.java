package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.interactive.response.gym.GymInfoSimple;
import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymInfo;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
import com.linkfeeling.platform.repo.gym.GymGroupUserRepository;
import com.linkfeeling.platform.repo.gym.GymInfoRepository;
import com.linkfeeling.platform.util.ArrayListUtil;
import com.linkfeeling.platform.util.BeanWriteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gym_info")
public class GymInfoController {
    @Autowired
    private GymInfoRepository gymInfoRepository;

    @Autowired
    private GymAdminUserRepository gymAdminUserRepository;

    @Autowired
    private GymGroupUserRepository gymGroupUserRepository;

    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response addInfo(String name, String city, String address, String label,
                            String phone, String logo_url, String display_img_urls, String mini_program_code_url){
        GymInfo gymInfo = new GymInfo(Long.valueOf(0),name,city,address,label,phone,logo_url,display_img_urls,mini_program_code_url);
        try {
            return ResponseUtil.newSuccess(gymInfoRepository.save(gymInfo));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.UPDATE)
    public Response updateInfo(Long id,Long member_count,String name, String city, String address, String label,
                            String phone, String logo_url, String display_img_urls, String mini_program_code_url){
        GymInfo gymInfo = new GymInfo(id,member_count,name,city,address,label,phone,logo_url,display_img_urls,mini_program_code_url);
        try {
            gymInfo = BeanWriteUtil.write(GymInfo.class,gymInfoRepository.findById(id).get(),gymInfo);
            return ResponseUtil.newSuccess(gymInfoRepository.save(gymInfo));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.UPDATE_ME)
    public Response updateMeInfo(HttpServletRequest servletRequest,Long member_count,String name, String city, String address, String label,
                               String phone, String logo_url, String display_img_urls, String mini_program_code_url){
        return GymControllerUtil.onMeRequest(servletRequest,gymAdminUserRepository,gymId ->
                gymInfoRepository.findById(gymId).isPresent()?updateInfo(gymId,member_count,name,city,address,label,phone,logo_url,display_img_urls,mini_program_code_url)
                :ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST,"gym not exist. [id="+gymId+"]")
                );
    }

    @RequestMapping(ActionContract.OPERATE.GET)
    public Response getGymInfo(Long id){
        Optional<GymInfo> gymInfo = gymInfoRepository.findById(id);
        if(gymInfo.isPresent()){
            return ResponseUtil.newSuccess(gymInfo.get());
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
        }
    }

    @RequestMapping(ActionContract.OPERATE.GET_ME)
    public Response getGymInfo(HttpServletRequest servletRequest){
        return GymControllerUtil.onMeRequest(servletRequest, gymAdminUserRepository, gymId -> getGymInfo(gymId));
    }

    @RequestMapping(ActionContract.OPERATE.LIST_ALL)
    public Response listAllGymInfo(){
        Iterable<GymInfo> gymInfo = gymInfoRepository.findAll();
        return ResponseUtil.newSuccess(ArrayListUtil.of(gymInfo).stream().map(gymInfoItem->{
            Optional<GymAdminUser> gymAdminUser = gymAdminUserRepository.findByGymId(gymInfoItem.getId());
            if(gymAdminUser.isPresent()){
                return new GymInfoSimple(gymInfoItem, InteractiveBeanUtil.from(gymAdminUser.get()));
            }else{
                return new GymInfoSimple(gymInfoItem, null);
            }
        }).collect(Collectors.toList()));
    }

    @RequestMapping(ActionContract.OPERATE.LIST_GROUP)
    public Response listGroupGymInfo(HttpServletRequest servletRequest){
        return GymControllerUtil.onGroupRequest(servletRequest, gymGroupUserRepository, gymIdList -> {
            Iterable<GymInfo> gymInfo = gymInfoRepository.findAllById(gymIdList);
            return ResponseUtil.newSuccess(ArrayListUtil.of(gymInfo).stream().map(gymInfoItem->{
                Optional<GymAdminUser> gymAdminUser = gymAdminUserRepository.findByGymId(gymInfoItem.getId());
                if(gymAdminUser.isPresent()){
                    return new GymInfoSimple(gymInfoItem, InteractiveBeanUtil.from(gymAdminUser.get()));
                }else{
                    return new GymInfoSimple(gymInfoItem, null);
                }
            }).collect(Collectors.toList()));
        });
    }

    @RequestMapping(ActionContract.OPERATE.DELETE)
    public Response deleteGymInfo(Long id){
        try {
            gymInfoRepository.deleteById(id);
            return ResponseUtil.newSuccess("delete gym info success [id="+id+"]");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
}
