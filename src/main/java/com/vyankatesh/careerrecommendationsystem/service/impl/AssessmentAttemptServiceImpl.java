package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Assessment;
import com.vyankatesh.careerrecommendationsystem.entity.AssessmentAttempt;
import com.vyankatesh.careerrecommendationsystem.entity.User;
import com.vyankatesh.careerrecommendationsystem.repository.AssessmentAttemptRepository;
import com.vyankatesh.careerrecommendationsystem.repository.AssessmentRepository;
import com.vyankatesh.careerrecommendationsystem.repository.UserRepository;
import com.vyankatesh.careerrecommendationsystem.service.AssessmentAttemptService;

@Service
public class AssessmentAttemptServiceImpl implements AssessmentAttemptService {

    private final AssessmentAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;

    public AssessmentAttemptServiceImpl(
            AssessmentAttemptRepository attemptRepository,
            UserRepository userRepository,
            AssessmentRepository assessmentRepository) {

        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public AssessmentAttempt startAssessment(
            Long userId,
            Long assessmentId) {

        Optional<AssessmentAttempt> existingAttempt =
                attemptRepository.findByUserIdAndAssessmentId(
                        userId,
                        assessmentId
                );

        if (existingAttempt.isPresent()) {
            return existingAttempt.get();
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new RuntimeException("Assessment not found"));

        AssessmentAttempt attempt = new AssessmentAttempt();

        attempt.setUser(user);
        attempt.setAssessment(assessment);
        attempt.setStartTime(LocalDateTime.now());
        attempt.setSubmitted(false);

        return attemptRepository.save(attempt);
    }

    @Override
    public Optional<AssessmentAttempt> getAttempt(
            Long userId,
            Long assessmentId) {

        return attemptRepository.findByUserIdAndAssessmentId(
                userId,
                assessmentId
        );
    }
}