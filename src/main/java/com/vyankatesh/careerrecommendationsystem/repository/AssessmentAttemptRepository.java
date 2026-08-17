package com.vyankatesh.careerrecommendationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.AssessmentAttempt;

public interface AssessmentAttemptRepository
        extends JpaRepository<AssessmentAttempt, Long> {

    Optional<AssessmentAttempt> findByUserIdAndAssessmentId(
            Long userId,
            Long assessmentId
    );
}