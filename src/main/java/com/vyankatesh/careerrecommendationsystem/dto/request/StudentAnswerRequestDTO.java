package com.vyankatesh.careerrecommendationsystem.dto.request;

public class StudentAnswerRequestDTO {

    private Long userId;

    private Long questionId;

    private Long optionId;

    public StudentAnswerRequestDTO() {
    }

    public StudentAnswerRequestDTO(Long userId, Long questionId, Long optionId) {
        this.userId = userId;
        this.questionId = questionId;
        this.optionId = optionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}