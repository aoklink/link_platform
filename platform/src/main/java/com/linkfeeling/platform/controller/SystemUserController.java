package com.linkfeeling.platform.controller;

import com.linkfeeling.platform.bean.SystemUser;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {
    @Autowired
    private SystemUserRepository systemUserRepository;
    @RequestMapping("/add")
    public Response addUser(String name, String password){
        try {
            systemUserRepository.save(new SystemUser(name,password));
            return ResponseUtil.newSuccess(name+" add success");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @RequestMapping("/verify")
    public Response verifyUser(String name, String password){
        try {
            return ResponseUtil.newSuccess(systemUserRepository.findById(name).get().getPassword().equals(password));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
}
