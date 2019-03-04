package com.linkfeeling.platform.controller.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.controller.ActionContract;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
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
    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response addUser(String name, String phone, String password, Long gymId){
        try {
            GymAdminUser gymAdminUser = new GymAdminUser(name,phone,md5DigestAsHex(password.getBytes()),gymId);
            GymAdminUser gymAdminUserResult = gymAdminUserRepository.save(gymAdminUser);
            return ResponseUtil.newSuccess(InteractiveBeanUtil.from(gymAdminUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.VERIFY)
    public Response verifyUser(Long gymId,String name, String password){
        try {
            Optional<GymAdminUser> gymAdminUser = gymAdminUserRepository.findByGymIdAndName(gymId,name);
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
