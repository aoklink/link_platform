package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymInfo;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymInfoRepository;
import com.linkfeeling.platform.util.ArrayListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/gym_info")
public class GymInfoController {
    @Autowired
    private GymInfoRepository gymInfoRepository;

    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response addInfo(String name, String city, String address, String label,
                            String phone, String logoUrl, String displayImgUrls, String miniProgramCodeUrl){
        GymInfo gymInfo = new GymInfo(name,city,address,label,phone,logoUrl,displayImgUrls,miniProgramCodeUrl);
        try {
            return ResponseUtil.newSuccess(gymInfoRepository.save(gymInfo));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.UPDATE)
    public Response updateInfo(Long id,String name, String city, String address, String label,
                            String phone, String logoUrl, String displayImgUrls, String miniProgramCodeUrl){
        GymInfo gymInfo = new GymInfo(id,name,city,address,label,phone,logoUrl,displayImgUrls,miniProgramCodeUrl);
        try {
            return ResponseUtil.newSuccess(gymInfoRepository.save(gymInfo));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
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

    @RequestMapping(ActionContract.OPERATE.LIST)
    public Response listGymInfo(){
        Iterable<GymInfo> gymInfo = gymInfoRepository.findAll();
        return ResponseUtil.newSuccess(ArrayListUtil.of(gymInfo));
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
