package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Assessment;
import com.vyankatesh.careerrecommendationsystem.entity.Result;
import com.vyankatesh.careerrecommendationsystem.entity.Stream;
import com.vyankatesh.careerrecommendationsystem.entity.StudentAnswer;
import com.vyankatesh.careerrecommendationsystem.entity.User;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.AssessmentRepository;

import com.vyankatesh.careerrecommendationsystem.repository.ResultRepository;
import com.vyankatesh.careerrecommendationsystem.repository.StreamRepository;
import com.vyankatesh.careerrecommendationsystem.repository.StudentAnswerRepository;
import com.vyankatesh.careerrecommendationsystem.repository.UserRepository;
import com.vyankatesh.careerrecommendationsystem.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;
    private final StreamRepository streamRepository;

    public ResultServiceImpl(ResultRepository resultRepository,
                             StudentAnswerRepository studentAnswerRepository,
                             UserRepository userRepository,
                             AssessmentRepository assessmentRepository,
                             StreamRepository streamRepository) {

        this.resultRepository = resultRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
        this.streamRepository = streamRepository;
    }

 
    @Override
    public Result calculateResult(Long userId, Long assessmentId) {

        // Check User
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        // Check Assessment
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assessment not found with id: " + assessmentId));

        // Check if Result already exists
        resultRepository.findByUserIdAndAssessmentId(userId, assessmentId)
                .ifPresent(result -> {
                    throw new IllegalStateException(
                            "You have already completed this assessment.");
                });

        // Get Student Answers
        List<StudentAnswer> studentAnswers =
                studentAnswerRepository.findByUserIdAndQuestionAssessmentId(userId, assessmentId);          studentAnswerRepository.findByQuestionAssessmentId(assessmentId);

        if (studentAnswers.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No answers found for assessment id: " + assessmentId);
        }

        // Calculate Score and Total Marks
        int score = 0;
        int totalMarks = 0;

        for (StudentAnswer answer : studentAnswers) {

            totalMarks += answer.getQuestion().getMarks();

            if (answer.getSelectedOption().getIsCorrect()) {
                score += answer.getQuestion().getMarks();
            }
        }

        // Calculate Percentage
        double percentage = ((double) score / totalMarks) * 100;

        // Decide Stream
        String streamName;

        if (percentage >= 75) {
            streamName = "Science";
        } else if (percentage >= 50) {
            streamName = "Commerce";
        } else {
            streamName = "Arts";
        }

        // Get Stream
        Stream stream = streamRepository.findByStreamName(streamName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stream not found with name: " + streamName));

        // Create Result
        Result result = new Result();
        result.setUser(user);
        result.setAssessment(assessment);
        result.setScore(score);
        result.setPercentage(percentage);
        result.setStream(stream);

        // Save Result
        return resultRepository.save(result);
    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Result not found with id: " + id));
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result getResultByUserAndAssessment(Long userId, Long assessmentId) {
        return resultRepository
                .findByUserIdAndAssessmentId(userId, assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found for user id "
                                        + userId
                                        + " and assessment id "
                                        + assessmentId));
    }
}