package com.linkfeeling.platform.bean.interactive.response.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymCoach;
import com.linkfeeling.platform.bean.jpa.gym.GymInfo;

import java.util.ArrayList;
import java.util.List;

public class GymCommonInfo{
    private List<GymAdminUser> gymAdminUserList;
    private List<GymClassSimple> gymClassList;
    private List<GymCoach> gymCoachList;
    private GymInfo gymInfo;

    public GymCommonInfo(GymInfo gymInfo,List<GymAdminUser> gymAdminUserList, List<GymClassSimple> gymClassList, List<GymCoach> gymCoachList) {
        this.gymInfo = gymInfo;
        this.gymAdminUserList = new ArrayList<>(gymAdminUserList);
        this.gymClassList = new ArrayList<>(gymClassList);
        this.gymCoachList = new ArrayList<>(gymCoachList);
    }

    public List<GymAdminUser> getGymAdminUserList() {
        return gymAdminUserList;
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
