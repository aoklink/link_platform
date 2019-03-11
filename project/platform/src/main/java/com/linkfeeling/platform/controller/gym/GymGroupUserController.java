package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymGroupUser;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.AllUserRepository;
import com.linkfeeling.platform.repo.gym.GymGroupUserRepository;
import com.linkfeeling.platform.util.BeanWriteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/api/gym_group_user")
public class GymGroupUserController {
    @Autowired
    private GymGroupUserRepository gymGroupUserRepository;

    @Autowired
    private AllUserRepository allUserRepository;

    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response addUser(String name, String phone, String password, String gym_id_array){
        if(allUserRepository.findByName(name).isPresent()){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+name);
        }

        try {
            GymGroupUser gymGroupUser = new GymGroupUser(name,phone,md5DigestAsHex(password.getBytes()),gym_id_array);
            GymGroupUser gymGroupUserResult = gymGroupUserRepository.save(gymGroupUser);
            return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymGroupUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.UPDATE)
    public Response updateUser(Long id, String name, String phone, String password, String gym_id_array){
        try {
            GymGroupUser gymGroupUser = new GymGroupUser(id,name,phone,password==null?null:md5DigestAsHex(password.getBytes()),gym_id_array);
            gymGroupUser = BeanWriteUtil.write(GymGroupUser.class,gymGroupUserRepository.findById(id).get(),gymGroupUser);
            GymGroupUser gymGroupUserResult = gymGroupUserRepository.save(gymGroupUser);
            return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymGroupUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.VERIFY)
    public Response verifyUser(String name, String password){
        try {
            Optional<GymGroupUser> gymAdminUser = gymGroupUserRepository.findByName(name);
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
