package com.linkfeeling.platform.data.play.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymPlayMember {
    private Long id;
    private String nickName;
    private String uid;
    private String phoneNum;
    private String gymName;
    private Date bindTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }
}
