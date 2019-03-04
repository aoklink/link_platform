package com.linkfeeling.platform.bean.jpa.gym;

import javax.persistence.*;

@Entity
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String priceInfo;
    @Lob
    private String content;
    private Long gymId;

    public GymClass() {
    }

    public GymClass(String title, String priceInfo, String content, Long gymId) {
        this.title = title;
        this.priceInfo = priceInfo;
        this.content = content;
        this.gymId = gymId;
    }

    public GymClass(Long id, String title, String priceInfo, String content, Long gymId) {
        this.id = id;
        this.title = title;
        this.priceInfo = priceInfo;
        this.content = content;
        this.gymId = gymId;
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
}
