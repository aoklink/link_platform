package com.linkfeeling.platform.bean.jpa.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymGroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
