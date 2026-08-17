package com.vyankatesh.careerrecommendationsystem.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.entity.AssessmentAttempt;
import com.vyankatesh.careerrecommendationsystem.service.AssessmentAttemptService;

@RestController
@RequestMapping("/api/attempts")
public class AssessmentAttemptController {

    private final AssessmentAttemptService attemptService;

    public AssessmentAttemptController(
            AssessmentAttemptService attemptService) {

        this.attemptService = attemptService;
    }

    @PostMapping("/start/{userId}/{assessmentId}")
    public ResponseEntity<AssessmentAttempt> startAssessment(
            @PathVariable Long userId,
            @PathVariable Long assessmentId) {

        AssessmentAttempt attempt =
                attemptService.startAssessment(
                        userId,
                        assessmentId
                );

        return ResponseEntity.ok(attempt);
    }

    @GetMapping("/{userId}/{assessmentId}")
    public ResponseEntity<AssessmentAttempt> getAttempt(
            @PathVariable Long userId,
            @PathVariable Long assessmentId) {

        Optional<AssessmentAttempt> attempt =
                attemptService.getAttempt(
                        userId,
                        assessmentId
                );

        return attempt
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }
}