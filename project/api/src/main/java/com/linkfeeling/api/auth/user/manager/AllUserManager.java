package com.linkfeeling.api.auth.user.manager;

import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.comsumer.GymAccountServer;
import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.api.controller.user.UserControllerUtil;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;

public class AllUserManager implements UserDetailsManager {
    @Autowired
    private GymAccountServerDelegater gymAccountServer;
    @Value("${app.auth.strict}")
    private boolean authStrict;
    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return gymAccountServer.userExist(UserControllerUtil.buildUserFindArg(username)).getCode() == ResponseDesc.OK.getCode();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Response response  = gymAccountServer.userFind(UserControllerUtil.buildUserFindArg(username));
        if(response.getCode() == ResponseDesc.OK.getCode()){
            String responseBody = ResponseUtil.getDataString(response);
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            String password = jsonObject.getJSONObject("user").getString("password");
            String beanClassName = jsonObject.getString("bean_class_name");
            String authority = "";
            if("SystemUser".equals(beanClassName)){
                authority = IUserAuthority.SYSTEM;
            }else if("GymAdminUser".equals(beanClassName)){
                authority = IUserAuthority.GYM_ADMIN;
            }else if("GymGroupUser".equals(beanClassName)){
                authority = IUserAuthority.GYM_GROUP;
            }
            if(StringUtils.isEmpty(authority)){
                throw new UsernameNotFoundException(username);
            }else{
                return new User(username, genPassword(password), Collections.singleton(new SimpleGrantedAuthority(authority)));
            }
        }else{
            throw new UsernameNotFoundException(username);
        }
    }


    private String genPassword(String raw){
        if(authStrict){
            return DigestUtils.md5DigestAsHex(raw.getBytes());
        }else{
            return raw;
        }
    }

    public Object getUserToResponse(String username){
        Response response  = gymAccountServer.userFind(UserControllerUtil.buildUserFindArg(username));
        if(response.getCode() == ResponseDesc.OK.getCode()){
            JSONObject jsonObject = JSONObject.parseObject(ResponseUtil.getDataString(response));
            JSONObject userObject = jsonObject.getJSONObject("user");
            userObject.put("password","******");
            return userObject;
        }else{
            return response;
        }
    }
}
