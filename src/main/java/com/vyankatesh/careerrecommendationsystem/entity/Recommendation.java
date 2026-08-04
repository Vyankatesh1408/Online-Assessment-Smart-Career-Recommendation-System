package com.vyankatesh.careerrecommendationsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "confidence_score", nullable = false)
    private Double confidenceScore;
    
    @ManyToOne
    @JoinColumn(name = "result_id", nullable = false)
    private Result result;
    
    @ManyToOne
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Recommendation() {
    }

    public Long getId() {
        return id;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}