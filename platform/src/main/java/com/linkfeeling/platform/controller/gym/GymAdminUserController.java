package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymGroupUser;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.AllUserRepository;
import com.linkfeeling.platform.repo.SystemUserRepository;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
import com.linkfeeling.platform.util.BeanWriteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/api/gym_admin_user")
public class GymAdminUserController {
    @Autowired
    private GymAdminUserRepository gymAdminUserRepository;

    @Autowired
    private AllUserRepository allUserRepository;
    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response addUser(String name, String phone, String password, Long gym_id){
        if(allUserRepository.findByName(name).isPresent()){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+name);
        }

        try {
            GymAdminUser gymAdminUser = new GymAdminUser(name,phone,md5DigestAsHex(password.getBytes()),gym_id);
            GymAdminUser gymAdminUserResult = gymAdminUserRepository.save(gymAdminUser);
            return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymAdminUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.GET_BIND)
    public Response getUser(Long gym_id){
        try {
            GymAdminUser gymAdminUser = gymAdminUserRepository.findByGymId(gym_id).get();
            if(gymAdminUser==null){
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }else{
                return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymAdminUser));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }



    @RequestMapping(ActionContract.OPERATE.UPDATE)
    public Response updateUser(Long id, String name, String phone, String password, Long gym_id){
        try {
            GymAdminUser gymAdminUser = new GymAdminUser(id,name,phone,password==null?null:md5DigestAsHex(password.getBytes()),gym_id);
            gymAdminUser = BeanWriteUtil.write(GymAdminUser.class,gymAdminUserRepository.findById(id).get(),gymAdminUser);
            GymAdminUser gymAdminUserResult = gymAdminUserRepository.save(gymAdminUser);
            return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymAdminUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.VERIFY)
    public Response verifyUser(Long gym_id,String name, String password){
        try {
            Optional<GymAdminUser> gymAdminUser = gymAdminUserRepository.findByGymIdAndName(gym_id,name);
            if(gymAdminUser.isPresent()){
                if(gymAdminUser.get().getPassword().equals(password)){
                    return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymAdminUser.get()));
                }else{
                    return ResponseUtil.newResponseWithDesc(ResponseDesc.PASSWORD_ERROR);
                }
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
}
