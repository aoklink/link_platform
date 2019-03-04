package com.linkfeeling.platform.bean.interactive.response;

public class SystemUser {
    private Long id;
    private String name;

    public SystemUser() {
    }

    public SystemUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
