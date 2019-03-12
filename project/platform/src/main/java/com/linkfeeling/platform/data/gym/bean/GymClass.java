package com.linkfeeling.platform.data.gym.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymClass {
    private Long id;
    private String title;
    private String priceInfo;
    private String content;
    private Long gymId;
    private Integer state;

    public GymClass() {
    }

    public GymClass(String title, String priceInfo, String content, Long gymId,Integer state) {
        this.title = title;
        this.priceInfo = priceInfo;
        this.content = content;
        this.gymId = gymId;
        this.state = state;
    }

    public GymClass(Long id, String title, String priceInfo, String content, Long gymId,Integer state) {
        this.id = id;
        this.title = title;
        this.priceInfo = priceInfo;
        this.content = content;
        this.gymId = gymId;
        this.state = state;
    }

    public Long getId() {
        return id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setId(long id) {
        this.id = id;
    }
}
