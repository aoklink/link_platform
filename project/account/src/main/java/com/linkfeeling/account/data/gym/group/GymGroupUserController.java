package com.linkfeeling.account.data.gym.group;

import com.linkfeeling.account.data.gym.group.bean.GymGroupUser;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.BeanWriteUtil;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/account/gym_group_user")
public class GymGroupUserController {
    @Autowired
    private GymGroupUserComponent gymGroupUserComponent;

    @RequestMapping(ControllerActionContract.OPERATE.ADD)
    public Response addUser(@RequestBody GymGroupUser gymGroupUser){
        if(gymGroupUserComponent.findByName(gymGroupUser.getName()).isPresent()){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+gymGroupUser.getName());
        }

        try {
            gymGroupUser.setPassword(md5DigestAsHex(gymGroupUser.getPassword().getBytes()));
            GymGroupUser gymGroupUserResult = gymGroupUserComponent.save(gymGroupUser);
            return ResponseUtil.newSuccess(gymGroupUserComponent.toResponse(gymGroupUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response updateUser(@RequestBody GymGroupUser gymGroupUser){
        try {
            gymGroupUser.setPassword(gymGroupUser.getPassword()==null?null:md5DigestAsHex(gymGroupUser.getPassword().getBytes()));
            gymGroupUser = BeanWriteUtil.write(GymGroupUser.class,gymGroupUserComponent.findById(gymGroupUser.getId()).get(),gymGroupUser);
            GymGroupUser gymGroupUserResult = gymGroupUserComponent.save(gymGroupUser);
            return ResponseUtil.newSuccess(gymGroupUserComponent.toResponse(gymGroupUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.VERIFY)
    public Response verifyUser(String name, String password){
        try {
            Optional<GymGroupUser> gymAdminUser = gymGroupUserComponent.findByName(name);
            if(gymAdminUser.isPresent()){
                if(gymAdminUser.get().getPassword().equals(password)){
                    return ResponseUtil.newSuccess(gymGroupUserComponent.toResponse(gymAdminUser.get()));
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
