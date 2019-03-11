package com.linkfeeling.platform.controller;

import com.linkfeeling.platform.bean.jpa.SystemUser;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.AllUserRepository;
import com.linkfeeling.platform.repo.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private AllUserRepository allUserRepository;

    @RequestMapping(ActionContract.OPERATE.ADD)
    public Response addUser(HttpServletRequest servletRequest,String name, String password){
        try {
            if("platform".equals(servletRequest.getParameter("api_admin"))){
                if(allUserRepository.findByName(name).isPresent()){
                    return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+name);
                }
                SystemUser user = systemUserRepository.save(new SystemUser(name, md5DigestAsHex(password.getBytes())));
                return ResponseUtil.newSuccess(InteractiveBeanUtil.from(user));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.INVALID_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping(ActionContract.OPERATE.VERIFY)
    public Response verifyUser(String name, String password){
        try {
            Optional<SystemUser> systemUser = systemUserRepository.findByName(name);
            if(systemUser.isPresent()){
                if(systemUser.get().getPassword().equals(password)){
                    return ResponseUtil.newSuccess(InteractiveBeanUtil.from(systemUser.get()));
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
