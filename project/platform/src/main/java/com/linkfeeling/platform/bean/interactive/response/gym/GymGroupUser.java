package com.linkfeeling.platform.bean.interactive.response.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymGroupUser {
    private Long id;
    private String name;
    private String phone;
    private String gymIdArray;

    public GymGroupUser(Long id, String name, String phone, String gymIdArray) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gymIdArray = gymIdArray;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGymIdArray() {
        return gymIdArray;
    }
}
