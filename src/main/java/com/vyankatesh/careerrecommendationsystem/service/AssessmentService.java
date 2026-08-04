package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Assessment;

public interface AssessmentService {

    Assessment saveAssessment(Assessment assessment);

    Assessment getAssessmentById(Long id);

    List<Assessment> getAllAssessments();

    Assessment updateAssessment(Long id, Assessment assessment);

    void deleteAssessment(Long id);

}