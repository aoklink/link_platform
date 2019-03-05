package com.linkfeeling.platform.bean.jpa.gym;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GymCoach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
