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
    
    
    
    @Column(name = "min_percentage", nullable = false)
    private Double minPercentage;

    @Column(name = "max_percentage", nullable = false)
    private Double maxPercentage;

    @Column(name = "required_skills", length = 1000)
    private String requiredSkills;

    @Column(name = "average_salary")
    private Double averageSalary;

    @Column(name = "roadmap", length = 1000)
    private String roadmap;
    
    public Career() {
    	
    }

    

    public Double getMinPercentage() {
		return minPercentage;
	}

	public void setMinPercentage(Double minPercentage) {
		this.minPercentage = minPercentage;
	}

	public Double getMaxPercentage() {
		return maxPercentage;
	}

	public void setMaxPercentage(Double maxPercentage) {
		this.maxPercentage = maxPercentage;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public Double getAverageSalary() {
		return averageSalary;
	}

	public void setAverageSalary(Double averageSalary) {
		this.averageSalary = averageSalary;
	}

	public String getRoadmap() {
		return roadmap;
	}

	public void setRoadmap(String roadmap) {
		this.roadmap = roadmap;
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