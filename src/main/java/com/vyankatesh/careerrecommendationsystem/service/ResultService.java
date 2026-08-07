package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.dto.response.ResultResponseDTO;
import com.vyankatesh.careerrecommendationsystem.entity.Result;

public interface ResultService {

	ResultResponseDTO calculateResult(Long userId, Long assessmentId);

    Result getResultById(Long id);

    List<Result> getAllResults();

    ResultResponseDTO getResultByUserAndAssessment(Long userId, Long assessmentId);
}