package com.linkfeeling.account.data.system.user;

import com.linkfeeling.account.data.UserControllerSupport;
import com.linkfeeling.account.data.common.user.CommonUserComponent;
import com.linkfeeling.account.data.gym.admin.bean.GymAdminUser;
import com.linkfeeling.account.data.system.user.bean.SystemUser;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.util.BeanWriteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/account/system_user")
public class SystemUserController {
    @Autowired
    private SystemUserComponent systemUserComponent;

    @Autowired
    private CommonUserComponent commonUserComponent;


    @RequestMapping(ControllerActionContract.OPERATE.ADD)
    public Response addUser( @RequestBody SystemUser systemUser){
        try {

            try {
                UserControllerSupport.checkExist(commonUserComponent,systemUser.getName(),systemUser.getPhone());
            } catch (UserControllerSupport.ExistException e) {
                return UserControllerSupport.genExistResponse(e);
            }

            systemUser.setPassword(md5DigestAsHex(systemUser.getPassword().getBytes()));
            SystemUser user = systemUserComponent.save(systemUser);
            return ResponseUtil.newSuccess(systemUserComponent.toResponse(user));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response updateUser( @RequestBody SystemUser systemUser){

        try {
            Optional<SystemUser> systemUserInDB =  systemUserComponent.findById(systemUser.getId());
            if(systemUserInDB.isPresent()){
                if(systemUser.getPhone().equals(systemUserInDB.get().getPhone())){
                    return doUpdate(systemUser,systemUserInDB);
                }else{
                    try {
                        UserControllerSupport.checkPhoneExist(commonUserComponent,systemUser.getPhone());
                    } catch (UserControllerSupport.ExistException e) {
                        return UserControllerSupport.genExistResponse(e);
                    }
                    return doUpdate(systemUser,systemUserInDB);
                }
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }


        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    private Response doUpdate(SystemUser systemUser,Optional<SystemUser> systemUserInDB)throws Exception{
        systemUser.setPassword(systemUser.getPassword()==null?null:md5DigestAsHex(systemUser.getPassword().getBytes()));
        systemUser = BeanWriteUtil.write(SystemUser.class,systemUserInDB.get(),systemUser);
        SystemUser systemUserResult = systemUserComponent.save(systemUser);
        return ResponseUtil.newSuccess(systemUserComponent.toResponse(systemUserResult));
    }

    @RequestMapping(ControllerActionContract.OPERATE.VERIFY)
    public Response verifyUser(@RequestBody SystemUser systemUserParam){
        try {
            Optional<SystemUser> systemUser = systemUserComponent.findByName(systemUserParam.getName());
            if(systemUser.isPresent()){
                if(systemUser.get().getPassword().equals(systemUserParam.getPassword())){
                    return ResponseUtil.newSuccess("verify passed");
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
    public Response delete(@RequestBody SystemUser systemUser){

        try {
            Optional<SystemUser> systemUserInDB = systemUserComponent.findById(systemUser.getId());
            if(systemUserInDB.isPresent()){
                systemUserComponent.delete(systemUser);
                return ResponseUtil.newSuccess("delete success");
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
            }
        }catch (Exception e){
            return ResponseUtil.newException(e);
        }
    }
}
