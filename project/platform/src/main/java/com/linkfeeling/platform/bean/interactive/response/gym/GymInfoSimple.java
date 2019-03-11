package com.linkfeeling.platform.bean.interactive.response.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linkfeeling.platform.bean.jpa.gym.GymInfo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymInfoSimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private Long memberCount;

    private GymAdminUser gymAdminUser;

    public GymInfoSimple(GymInfo gymInfo,GymAdminUser gymAdminUser) {
        this.id = gymInfo.getId();
        this.name = gymInfo.getName();
        this.city = gymInfo.getCity();
        this.memberCount = gymInfo.getMemberCount();
        this.gymAdminUser = gymAdminUser;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public GymAdminUser getGymAdminUser() {
        return gymAdminUser;
    }

    public Long getMemberCount() {
        return memberCount;
    }
}
