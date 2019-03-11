package com.linkfeeling.account.data.system.user;

import com.linkfeeling.account.data.system.user.bean.SystemUser;
import com.linkfeeling.common.controller.ControllerActionContract;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(ControllerActionContract.OPERATE.ADD)
    public Response addUser(HttpServletRequest servletRequest, String name, String password){
        try {
            if("platform".equals(servletRequest.getParameter("api_admin"))){
                if(systemUserComponent.findByName(name).isPresent()){
                    return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+name);
                }
                SystemUser user = systemUserComponent.save(new SystemUser(name, md5DigestAsHex(password.getBytes())));
                return ResponseUtil.newSuccess(systemUserComponent.toResponse(user));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.INVALID_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ControllerActionContract.OPERATE.VERIFY)
    public Response verifyUser(String name, String password){
        try {
            Optional<SystemUser> systemUser = systemUserComponent.findByName(name);
            if(systemUser.isPresent()){
                if(systemUser.get().getPassword().equals(password)){
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
}
