package com.vyankatesh.careerrecommendationsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.StudentAnswer;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    List<StudentAnswer> findByUserId(Long userId);

    List<StudentAnswer> findByQuestionAssessmentId(Long assessmentId);

}