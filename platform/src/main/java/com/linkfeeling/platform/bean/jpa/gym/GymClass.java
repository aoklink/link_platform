package com.linkfeeling.platform.bean.jpa.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String priceInfo;
    @Lob
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
}
