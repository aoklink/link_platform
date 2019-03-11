package com.linkfeeling.account.data.gym.admin;


import com.linkfeeling.account.data.gym.admin.bean.GymAdminUser;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.BeanWriteUtil;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/account/gym_admin_user")
public class GymAdminUserController {
    @Autowired
    private GymAdminUserComponent gymAdminUserComponent;

    @RequestMapping(ControllerActionContract.OPERATE.ADD)
    public Response addUser(String name, String phone, String password, Long gym_id){
        if(gymAdminUserComponent.findByName(name).isPresent()){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+name);
        }

        try {
            GymAdminUser gymAdminUser = new GymAdminUser(name,phone,md5DigestAsHex(password.getBytes()),gym_id);
            GymAdminUser gymAdminUserResult = gymAdminUserComponent.save(gymAdminUser);
            return ResponseUtil.newSuccess(gymAdminUserComponent.toResponse(gymAdminUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response updateUser(Long id, String name, String phone, String password, Long gym_id){
        try {
            GymAdminUser gymAdminUser = new GymAdminUser(id,name,phone,password==null?null:md5DigestAsHex(password.getBytes()),gym_id);
            gymAdminUser = BeanWriteUtil.write(GymAdminUser.class, gymAdminUserComponent.findById(id).get(),gymAdminUser);
            GymAdminUser gymAdminUserResult = gymAdminUserComponent.save(gymAdminUser);
            return ResponseUtil.newSuccess(gymAdminUserComponent.toResponse(gymAdminUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.VERIFY)
    public Response verifyUser(Long gym_id,String name, String password){
        try {
            Optional<GymAdminUser> gymAdminUser = gymAdminUserComponent.findByGymIdAndName(gym_id,name);
            if(gymAdminUser.isPresent()){
                if(gymAdminUser.get().getPassword().equals(password)){
                    return ResponseUtil.newSuccess(gymAdminUserComponent.toResponse(gymAdminUser.get()));
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
