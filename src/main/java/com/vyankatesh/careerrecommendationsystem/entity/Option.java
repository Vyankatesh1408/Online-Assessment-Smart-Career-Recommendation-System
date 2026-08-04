package com.vyankatesh.careerrecommendationsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;
    
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Option() {
    }

    public Long getId() {
        return id;
    }

    public String getOptionText() {
        return optionText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}