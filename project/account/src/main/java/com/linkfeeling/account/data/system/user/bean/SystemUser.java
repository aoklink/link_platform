package com.linkfeeling.account.data.system.user.bean;

public class SystemUser {

    private Long id;
    private String name;

    private String password;

    private String phone;

    public SystemUser() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public SystemUser(Long id, String name, String password, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

