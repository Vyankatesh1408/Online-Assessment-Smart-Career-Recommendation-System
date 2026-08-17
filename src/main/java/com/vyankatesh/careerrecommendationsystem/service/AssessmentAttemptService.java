package com.vyankatesh.careerrecommendationsystem.service;

import java.util.Optional;

import com.vyankatesh.careerrecommendationsystem.entity.AssessmentAttempt;

public interface AssessmentAttemptService {

    AssessmentAttempt startAssessment(Long userId, Long assessmentId);

    Optional<AssessmentAttempt> getAttempt(
            Long userId,
            Long assessmentId
    );
}