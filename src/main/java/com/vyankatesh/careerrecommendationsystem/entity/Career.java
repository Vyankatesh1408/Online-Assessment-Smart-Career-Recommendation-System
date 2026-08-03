package com.vyankatesh.careerrecommendationsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "career_name", nullable = false, unique = true)
    private String careerName;

    @Column(length = 500)
    private String description;

    public Career() {
    }

    public Long getId() {
        return id;
    }

    public String getCareerName() {
        return careerName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}