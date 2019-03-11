package com.linkfeeling.account.data.system.user.bean;

public class SystemUser {

    private Long id;
    private String name;

    private String password;

    public SystemUser() {
    }

    public Long getId() {
        return id;
    }

    public SystemUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public SystemUser(Long id, String name, String password) {
        this.id = id;
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

