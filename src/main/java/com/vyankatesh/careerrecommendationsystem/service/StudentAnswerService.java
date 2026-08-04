package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.StudentAnswer;

public interface StudentAnswerService {

    StudentAnswer saveStudentAnswer(StudentAnswer studentAnswer);

    StudentAnswer getStudentAnswerById(Long id);

    List<StudentAnswer> getAllStudentAnswers();

    List<StudentAnswer> getStudentAnswersByUserId(Long userId);

    List<StudentAnswer> getStudentAnswersByAssessmentId(Long assessmentId);

    StudentAnswer updateStudentAnswer(Long id, StudentAnswer studentAnswer);

    void deleteStudentAnswer(Long id);

}
