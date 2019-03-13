package com.linkfeeling.account.data.common.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.account.data.common.user.bean.CommonUserWrapper;
import com.linkfeeling.account.data.gym.admin.GymAdminUserComponent;
import com.linkfeeling.account.data.gym.admin.bean.GymAdminUser;
import com.linkfeeling.account.data.gym.group.GymGroupUserComponent;
import com.linkfeeling.account.data.gym.group.bean.GymGroupUser;
import com.linkfeeling.account.data.system.user.SystemUserComponent;
import com.linkfeeling.account.data.system.user.bean.SystemUser;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommonUserComponent {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SystemUserComponent systemUserComponent;

    @Autowired
    private GymAdminUserComponent gymAdminUserComponent;

    @Autowired
    private GymGroupUserComponent gymGroupUserComponent;

    public boolean exist(String phone) {
        return systemUserComponent.findByNameOrPhone(phone).isPresent()
                || gymAdminUserComponent.findByNameOrPhone(phone).isPresent()
                || gymGroupUserComponent.findByNameOrPhone(phone).isPresent();
    }

    public Response findUser(String phone) {
        Object result = getUser(phone);
        if(result==null){
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
        }else{
            return ResponseUtil.newSuccess(new CommonUserWrapper(result,result.getClass().getSimpleName()));
        }
    }

    private Object getUser(String phone){
        Optional<SystemUser> systemUser = systemUserComponent.findByNameOrPhone(phone);
        if(systemUser.isPresent()) return systemUser.get();
        Optional<GymAdminUser> gymAdminUser =  gymAdminUserComponent.findByNameOrPhone(phone);
        if(gymAdminUser.isPresent()) return gymAdminUser.get();
        Optional<GymGroupUser> gymGroupUser = gymGroupUserComponent.findByNameOrPhone(phone);
        if(gymGroupUser.isPresent()) return gymGroupUser.get();
        return null;
    }
}
