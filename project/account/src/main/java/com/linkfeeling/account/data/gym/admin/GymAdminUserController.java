package com.linkfeeling.account.data.gym.admin;


import com.linkfeeling.account.data.UserControllerSupport;
import com.linkfeeling.account.data.common.user.CommonUserComponent;
import com.linkfeeling.account.data.gym.admin.bean.GymAdminUser;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.BeanWriteUtil;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/account/gym_admin_user")
public class GymAdminUserController {
    @Autowired
    private GymAdminUserComponent gymAdminUserComponent;

    @Autowired
    private CommonUserComponent commonUserComponent;

    @RequestMapping(ControllerActionContract.OPERATE.ADD)
    public Response addUser(@RequestBody GymAdminUser gymAdminUser){
        try {
            UserControllerSupport.checkExist(commonUserComponent,gymAdminUser.getName(),gymAdminUser.getPhone());
        } catch (UserControllerSupport.ExistException e) {
            return UserControllerSupport.genExistResponse(e);
        }
        try {
            gymAdminUser.setPassword(md5DigestAsHex(gymAdminUser.getPassword().getBytes()));
            GymAdminUser gymAdminUserResult = gymAdminUserComponent.save(gymAdminUser);
            return ResponseUtil.newSuccess(gymAdminUserComponent.toResponse(gymAdminUserResult));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.GET_BIND)
    public Response getUserByGymId(@RequestBody GymAdminUser gymAdminUser){

        try {
            Optional<GymAdminUser> gymAdminUserInDB = gymAdminUserComponent.findByGymId(gymAdminUser.getGymId());
            if(gymAdminUserInDB.isPresent()){
                return ResponseUtil.newSuccess(gymAdminUserComponent.toResponse(gymAdminUserInDB.get()));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response updateUser(@RequestBody GymAdminUser gymAdminUser){
        try {
            Optional<GymAdminUser> gymAdminUserInDB =  gymAdminUserComponent.findById(gymAdminUser.getId());
            if(gymAdminUserInDB.isPresent()){
                if(gymAdminUser.getPhone().equals(gymAdminUserInDB.get().getPhone())){
                    return doUpdate(gymAdminUser,gymAdminUserInDB);
                }else{
                    try {
                        UserControllerSupport.checkPhoneExist(commonUserComponent,gymAdminUser.getPhone());
                    } catch (UserControllerSupport.ExistException e) {
                        return UserControllerSupport.genExistResponse(e);
                    }
                    return doUpdate(gymAdminUser,gymAdminUserInDB);
                }
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }


        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    private Response doUpdate(GymAdminUser gymAdminUser,Optional<GymAdminUser> gymAdminUserInDB)throws Exception{
        gymAdminUser.setPassword(gymAdminUser.getPassword()==null?null:md5DigestAsHex(gymAdminUser.getPassword().getBytes()));
        gymAdminUser = BeanWriteUtil.write(GymAdminUser.class,gymAdminUserInDB.get(),gymAdminUser);
        GymAdminUser gymAdminUserResult = gymAdminUserComponent.save(gymAdminUser);
        return ResponseUtil.newSuccess(gymAdminUserComponent.toResponse(gymAdminUserResult));
    }

    @RequestMapping(ControllerActionContract.OPERATE.VERIFY)
    public Response verifyUser(@RequestBody GymAdminUser gymAdminUserParam){
        try {
            Optional<GymAdminUser> gymAdminUser = gymAdminUserComponent.findByGymIdAndName(gymAdminUserParam.getGymId(),gymAdminUserParam.getName());
            if(gymAdminUser.isPresent()){
                if(gymAdminUser.get().getPassword().equals(gymAdminUserParam.getPassword())){
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

    @RequestMapping(ControllerActionContract.OPERATE.DELETE)
    public Response delete(@RequestBody GymAdminUser gymAdminUser){

        try {
            Optional<GymAdminUser> gymAdminUserInDB = gymAdminUserComponent.findById(gymAdminUser.getId());
            if(gymAdminUserInDB.isPresent()){
                gymAdminUserComponent.delete(gymAdminUser);
                return ResponseUtil.newSuccess("delete success");
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.LIST)
    public Response listUser(@RequestBody GymAdminUser gymAdminUser){
        try {
            List<GymAdminUser> gymAdminUserInDB = gymAdminUserComponent.findAllByGymId(gymAdminUser.getGymId());
            gymAdminUserInDB.forEach(it->gymAdminUserComponent.toResponse(it));
            return ResponseUtil.newSuccess(gymAdminUserInDB);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
}
