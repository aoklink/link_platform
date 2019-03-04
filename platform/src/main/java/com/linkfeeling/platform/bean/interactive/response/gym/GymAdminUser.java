package com.linkfeeling.platform.bean.interactive.response.gym;

public class GymAdminUser {
    private Long id;

    private String name;
    private String phone;
    private Long gymId;

    public GymAdminUser(Long id, String name, String phone, Long gymId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
