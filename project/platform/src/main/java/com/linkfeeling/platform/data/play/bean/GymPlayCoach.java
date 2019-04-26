package com.linkfeeling.platform.data.play.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;
// `phone_num` varchar(11) NOT NULL COMMENT '手机号',
//         `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
//         `head_icon` varchar(256) DEFAULT NULL,
//         `uid` varchar(32) NOT NULL COMMENT '教练id',
//         `user_type` varchar(32) NOT NULL,
//         `build_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymPlayCoach {
    private String uid;
    private String userName;
    private String phoneNum;
    private String userType;
    private Date buildTime;
    private String headIcon;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }
}
