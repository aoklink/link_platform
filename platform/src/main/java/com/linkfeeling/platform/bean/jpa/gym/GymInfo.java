package com.linkfeeling.platform.bean.jpa.gym;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GymInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String address;

    private String label;

    private String phone;

    private String logoUrl;

    private String displayImgUrls;

    private String miniProgramCodeUrl;

    public GymInfo(String name, String city, String address, String label, String phone, String logoUrl, String displayImgUrls, String miniProgramCodeUrl) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.label = label;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.displayImgUrls = displayImgUrls;
        this.miniProgramCodeUrl = miniProgramCodeUrl;
    }

    public GymInfo(Long id,String name, String city, String address, String label, String phone, String logoUrl, String displayImgUrls, String miniProgramCodeUrl) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.label = label;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.displayImgUrls = displayImgUrls;
        this.miniProgramCodeUrl = miniProgramCodeUrl;
    }

    public GymInfo() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDisplayImgUrls() {
        return displayImgUrls;
    }

    public void setDisplayImgUrls(String displayImgUrls) {
        this.displayImgUrls = displayImgUrls;
    }

    public String getMiniProgramCodeUrl() {
        return miniProgramCodeUrl;
    }

    public void setMiniProgramCodeUrl(String miniProgramCodeUrl) {
        this.miniProgramCodeUrl = miniProgramCodeUrl;
    }
}
