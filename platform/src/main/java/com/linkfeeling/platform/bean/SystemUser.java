package com.linkfeeling.platform.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemUser {

    @Id
    private String name;

    private String password;

    public SystemUser() {
    }

    public SystemUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
