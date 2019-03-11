//package com.linkfeeling.platform.interactive.util;
//
//import com.linkfeeling.platform.bean.interactive.response.SystemUser;
//import com.linkfeeling.platform.bean.interactive.response.gym.GymAdminUser;
//import com.linkfeeling.platform.bean.interactive.response.gym.GymClassSimple;
//import com.linkfeeling.platform.bean.interactive.response.gym.GymGroupUser;
//import com.linkfeeling.platform.bean.jpa.gym.GymClass;
//
//public class InteractiveBeanUtil {
//    public static SystemUser from(com.linkfeeling.platform.bean.jpa.SystemUser systemUser) {
//        return new SystemUser(systemUser.getId(),systemUser.getName());
//    }
//
//    public static GymClassSimple from(GymClass gymClass){
//        return new GymClassSimple(gymClass.getId(),gymClass.getTitle(),gymClass.getPriceInfo(),gymClass.getGymId());
//    }
//
//    public static GymAdminUser from(com.linkfeeling.platform.bean.jpa.gym.GymAdminUser gymAdminUser) {
//        return new GymAdminUser(gymAdminUser.getId(),gymAdminUser.getName(),gymAdminUser.getPhone(),gymAdminUser.getGymId());
//    }
//
//    public static GymGroupUser from(com.linkfeeling.platform.bean.jpa.gym.GymGroupUser gymGroupUser){
//        return new GymGroupUser(gymGroupUser.getId(),gymGroupUser.getName(),gymGroupUser.getPhone(),gymGroupUser.getGymIdArray());
//    }
//}
