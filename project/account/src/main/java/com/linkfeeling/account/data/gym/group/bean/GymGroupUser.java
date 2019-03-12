package com.linkfeeling.account.data.gym.group.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymGroupUser {
    private Long id;

    private String name;
    private String phone;
    private String password;
    private String gymIdArray;

    public GymGroupUser() {
    }

    public GymGroupUser(Long id, String name, String phone, String password, String gymIdArray) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gymIdArray = gymIdArray;
    }

    public GymGroupUser(String name, String phone, String password, String gymIdArray) {
        this.name = name;
        this.phone = phone;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public String getGymIdArray() {
        return gymIdArray;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGymIdArray(String gymIdArray) {
        this.gymIdArray = gymIdArray;
    }

    public void setId(long id) {
        this.id= id;
    }
}
