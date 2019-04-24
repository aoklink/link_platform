package com.linkfeeling.platform.data.play.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymPlayCoach {
    private Long id;
    private Long gymId;
    private String name;
    private String phone;
    private Date joinTime;
    private String memberArray;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public String getMemberArray() {
        return memberArray;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public void setMemberArray(String memberArray) {
        this.memberArray = memberArray;
    }
}
