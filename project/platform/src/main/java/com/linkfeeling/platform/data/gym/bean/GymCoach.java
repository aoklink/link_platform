package com.linkfeeling.platform.data.gym.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymCoach {
    private Long id;

    private String name;
    private String label;
    private Long gymId;

    public GymCoach() {
    }

    public GymCoach(String name, String label, Long gymId) {
        this.name = name;
        this.label = label;
        this.gymId = gymId;
    }

    public GymCoach(Long id, String name, String label, Long gymId) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.gymId = gymId;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public void setId(long id) {
        this.id = id;
    }
}
