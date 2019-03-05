package com.linkfeeling.platform.bean.interactive.response.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymClassSimple {
    private Long id;
    private String title;
    private String priceInfo;
    private Long gymId;

    public GymClassSimple(Long id, String title, String priceInfo, Long gymId) {
        this.id = id;
        this.title = title;
        this.priceInfo = priceInfo;
        this.gymId = gymId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
