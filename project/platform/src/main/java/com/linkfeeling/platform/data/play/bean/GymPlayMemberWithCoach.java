package com.linkfeeling.platform.data.play.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymPlayMemberWithCoach {
    private String coachUid;
    private String studentUid;
    private String studentNick;
    private Long gymId;
    private Date bindTime;

    public String getCoachUid() {
        return coachUid;
    }

    public void setCoachUid(String coachUid) {
        this.coachUid = coachUid;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }

    public String getStudentNick() {
        return studentNick;
    }

    public void setStudentNick(String studentNick) {
        this.studentNick = studentNick;
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
