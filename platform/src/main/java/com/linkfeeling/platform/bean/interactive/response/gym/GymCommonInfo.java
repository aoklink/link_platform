package com.linkfeeling.platform.bean.interactive.response.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linkfeeling.platform.bean.jpa.gym.GymCoach;
import com.linkfeeling.platform.bean.jpa.gym.GymInfo;

import java.util.ArrayList;
import java.util.List;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymCommonInfo{
    private GymAdminUser gymAdminUser;
    private List<GymClassSimple> gymClassList;
    private List<GymCoach> gymCoachList;
    private GymInfo gymInfo;

    public GymCommonInfo(GymInfo gymInfo,GymAdminUser gymAdminUser, List<GymClassSimple> gymClassList, List<GymCoach> gymCoachList) {
        this.gymInfo = gymInfo;
        this.gymAdminUser = gymAdminUser;
        this.gymClassList = new ArrayList<>(gymClassList);
        this.gymCoachList = new ArrayList<>(gymCoachList);
    }

    public GymAdminUser getGymAdminUser() {
        return gymAdminUser;
    }

    public List<GymClassSimple> getGymClassList() {
        return gymClassList;
    }

    public List<GymCoach> getGymCoachList() {
        return gymCoachList;
    }

    public GymInfo getGymInfo() {
        return gymInfo;
    }
}
