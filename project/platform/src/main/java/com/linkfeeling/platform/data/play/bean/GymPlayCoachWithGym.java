package com.linkfeeling.platform.data.play.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymPlayCoachWithGym {
    private String coachUid;
    private Long gymId;
    private Date bindTime;

    public String getCoachUid() {
        return coachUid;
    }

    public void setCoachUid(String coachUid) {
        this.coachUid = coachUid;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }
}
