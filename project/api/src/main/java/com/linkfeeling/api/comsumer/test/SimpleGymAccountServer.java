//package com.linkfeeling.api.comsumer.test;
//
//import com.linkfeeling.api.comsumer.GymAccountServer;
//import com.linkfeeling.common.interactive.response.Response;
//import com.linkfeeling.common.interactive.response.ResponseDesc;
//import com.linkfeeling.common.interactive.util.ResponseUtil;
//
//public class SimpleGymAccountServer implements GymAccountServer {
//    @Override
//    public String userExist(String requestJson) {
////        return ResponseUtil.newSuccess("yes");
//        return "";
//    }
//
//    @Override
//    public String userFind(String requestJson) {
////        return ResponseUtil.newSuccess("{\"password\":\"a321df15edf194db5b6fc445911da1b2\",\"bean_class_name\":\"SystemUser\"}");
//        return "";
//    }
//
//    @Override
//    public Response systemUserAdd(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//
//    @Override
//    public Response gymAdminUserAdd(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//
//    @Override
//    public Response systemUserUpdate(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//
//    @Override
//    public Response gymAdminUserUpdate(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//
//    @Override
//    public Response systemUserDelete(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//
//    @Override
//    public Response gymAdminUserDelete(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//
//    @Override
//    public Response gymAdminUserGetByGymId(String requestJson) {
//        return ResponseUtil.newResponseWithDesc(ResponseDesc.SERVICE_ERROR);
//    }
//}
