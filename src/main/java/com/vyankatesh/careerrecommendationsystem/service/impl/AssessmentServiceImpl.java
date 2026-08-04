package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Assessment;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.AssessmentRepository;
import com.vyankatesh.careerrecommendationsystem.service.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    @Override
    public Assessment getAssessmentById(Long id) {
        return assessmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assessment not found with id: " + id));
    }

    @Override
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    @Override
    public Assessment updateAssessment(Long id, Assessment assessment) {

        Assessment existingAssessment = assessmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assessment not found with id: " + id));

        existingAssessment.setTitle(assessment.getTitle());
        existingAssessment.setDescription(assessment.getDescription());
        existingAssessment.setDuration(assessment.getDuration());
        existingAssessment.setTotalQuestions(assessment.getTotalQuestions());
        existingAssessment.setCategory(assessment.getCategory());

        return assessmentRepository.save(existingAssessment);
    }

    @Override
    public void deleteAssessment(Long id) {

        Assessment existingAssessment = assessmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assessment not found with id: " + id));

        assessmentRepository.delete(existingAssessment);
    }
}