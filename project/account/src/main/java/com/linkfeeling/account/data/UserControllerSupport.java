package com.linkfeeling.account.data;

import com.linkfeeling.account.data.common.user.CommonUserComponent;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;
import com.linkfeeling.common.interactive.util.ResponseUtil;

public class UserControllerSupport {
    public static void checkExist(CommonUserComponent commonUserComponent,String name,String phone)throws ExistException{
        if(commonUserComponent.exist(name)){
            throw new ExistException("username already exist.[name="+name+"]");
        }

        if(commonUserComponent.exist(phone)){
            throw new ExistException("phone already exist.[phone="+phone+"]");
        }
    }

    public static void checkPhoneExist(CommonUserComponent commonUserComponent,String phone)throws ExistException{
        if(commonUserComponent.exist(phone)){
            throw new ExistException("phone already exist.[phone="+phone+"]");
        }
    }

    public static Response genExistResponse(ExistException exception){
        return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,exception.getMessage());
    }

    public static class ExistException extends Exception{
        public ExistException(String message) {
            super(message);
        }
    }
}
