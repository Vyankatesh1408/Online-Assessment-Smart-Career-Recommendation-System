package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Result;

public interface ResultService {

    Result calculateResult(Long userId, Long assessmentId);

    Result getResultById(Long id);

    List<Result> getAllResults();

    Result getResultByUserAndAssessment(Long userId, Long assessmentId);

}