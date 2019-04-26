package com.linkfeeling.platform.data.play.bean.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymMemberTrans {
//    {"student_uid":"","from_coach_uid":"(可空)","to_coach_uid":""}
    private String studentUidArray;
    private String fromCoachUid;
    private String toCoachUid;
    private Long gymId;

    public String getStudentUidArray() {
        return studentUidArray;
    }

    public void setStudentUidArray(String studentUidArray) {
        this.studentUidArray = studentUidArray;
    }

    public String getFromCoachUid() {
        return fromCoachUid;
    }

    public void setFromCoachUid(String fromCoachUid) {
        this.fromCoachUid = fromCoachUid;
    }

    public String getToCoachUid() {
        return toCoachUid;
    }

    public void setToCoachUid(String toCoachUid) {
        this.toCoachUid = toCoachUid;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
