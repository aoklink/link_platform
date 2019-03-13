package com.linkfeeling.platform.data.gym.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymInfoListItem {
    GymInfo gymInfo;
    GymAdminUser gymAdminUser;

    public GymInfoListItem(GymInfo gymInfo, GymAdminUser gymAdminUser) {
        this.gymInfo = gymInfo;
        this.gymAdminUser = gymAdminUser;
    }

    public GymInfo getGymInfo() {
        return gymInfo;
    }

    public void setGymInfo(GymInfo gymInfo) {
        this.gymInfo = gymInfo;
    }

    public GymAdminUser getGymAdminUser() {
        return gymAdminUser;
    }

    public void setGymAdminUser(GymAdminUser gymAdminUser) {
        this.gymAdminUser = gymAdminUser;
    }
}
