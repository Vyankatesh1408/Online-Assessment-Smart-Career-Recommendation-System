package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.StudentAnswer;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.StudentAnswerRepository;
import com.vyankatesh.careerrecommendationsystem.service.StudentAnswerService;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;

    public StudentAnswerServiceImpl(StudentAnswerRepository studentAnswerRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
    }

    @Override
    public StudentAnswer saveStudentAnswer(StudentAnswer studentAnswer) {
        return studentAnswerRepository.save(studentAnswer);
    }

    @Override
    public StudentAnswer getStudentAnswerById(Long id) {
        return studentAnswerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Answer not found with id: " + id));
    }

    @Override
    public List<StudentAnswer> getAllStudentAnswers() {
        return studentAnswerRepository.findAll();
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByUserId(Long userId) {
        return studentAnswerRepository.findByUserId(userId);
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByAssessmentId(Long assessmentId) {
        return studentAnswerRepository.findByQuestionAssessmentId(assessmentId);
    }

    @Override
    public StudentAnswer updateStudentAnswer(Long id, StudentAnswer studentAnswer) {

        StudentAnswer existingStudentAnswer = studentAnswerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Answer not found with id: " + id));

        existingStudentAnswer.setUser(studentAnswer.getUser());
        existingStudentAnswer.setQuestion(studentAnswer.getQuestion());
        existingStudentAnswer.setSelectedOption(studentAnswer.getSelectedOption());

        return studentAnswerRepository.save(existingStudentAnswer);
    }

    @Override
    public void deleteStudentAnswer(Long id) {

        StudentAnswer existingStudentAnswer = studentAnswerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Answer not found with id: " + id));

        studentAnswerRepository.delete(existingStudentAnswer);
    }
}