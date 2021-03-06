package com.linkfeeling.platform.data.gym.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymAdminUser {
    private Long id;

    private String name;
    private String phone;
    private String password;
    private Long gymId;

    public GymAdminUser() {
    }

    public GymAdminUser(String name, String phone, String password, Long gymId) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gymId = gymId;
    }

    public GymAdminUser(Long id, String name, String phone, String password, Long gymId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gymId = gymId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
