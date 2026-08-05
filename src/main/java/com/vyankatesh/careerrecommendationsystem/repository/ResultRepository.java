package com.vyankatesh.careerrecommendationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByUserIdAndAssessmentId(Long userId, Long assessmentId);

}