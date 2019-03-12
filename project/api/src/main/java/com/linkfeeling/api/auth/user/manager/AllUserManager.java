package com.linkfeeling.api.auth.user.manager;

import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.api.comsumer.GymAccountServer;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
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
    private GymAccountServer gymAccountServer;
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
        return gymAccountServer.userExist(mkRequestJson(username)).getCode() == ResponseDesc.OK.getCode();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Response<String> response  = gymAccountServer.userFind(mkRequestJson(username));
        if(response.getCode() == ResponseDesc.OK.getCode()){
            String responseBody = response.getData();
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            String password = jsonObject.getString("password");
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
                return new User(username, genPassword(password), Collections.singleton(new SimpleGrantedAuthority(IUserAuthority.SYSTEM)));
            }
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

    private String mkRequestJson(String username){
        return "{\"phone\":"+username+"}";
    }

    private String genPassword(String raw){
        if(authStrict){
            return DigestUtils.md5DigestAsHex(raw.getBytes());
        }else{
            return raw;
        }
    }

    public Object getUserToResponse(String username){
        Response<String> response  = gymAccountServer.userFind(mkRequestJson(username));
        if(response.getCode() == ResponseDesc.OK.getCode()){
            JSONObject jsonObject = JSONObject.parseObject(response.getData());
            jsonObject.remove("password");
            jsonObject.remove("bean_class_name");
            return jsonObject;
        }else{
            return response;
        }
    }
}
