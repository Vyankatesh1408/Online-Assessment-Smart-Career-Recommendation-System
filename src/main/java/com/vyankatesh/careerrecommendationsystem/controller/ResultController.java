package com.vyankatesh.careerrecommendationsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.entity.Result;
import com.vyankatesh.careerrecommendationsystem.service.ResultService;
import com.vyankatesh.careerrecommendationsystem.dto.response.ResultResponseDTO;
@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/calculate/{userId}/{assessmentId}")
    public ResultResponseDTO calculateResult(@PathVariable Long userId,
                                  @PathVariable Long assessmentId) {

        return resultService.calculateResult(userId, assessmentId);
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/user/{userId}/assessment/{assessmentId}")
    public ResultResponseDTO getResultByUserAndAssessment(@PathVariable Long userId,
                                               @PathVariable Long assessmentId) {

        return resultService.getResultByUserAndAssessment(userId, assessmentId);
    }
}