package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.StudentAnswer;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.StudentAnswerRepository;
import com.vyankatesh.careerrecommendationsystem.service.StudentAnswerService;
import com.vyankatesh.careerrecommendationsystem.dto.request.StudentAnswerRequestDTO;
import com.vyankatesh.careerrecommendationsystem.entity.Option;
import com.vyankatesh.careerrecommendationsystem.entity.Question;
import com.vyankatesh.careerrecommendationsystem.entity.User;
import com.vyankatesh.careerrecommendationsystem.repository.OptionRepository;
import com.vyankatesh.careerrecommendationsystem.repository.QuestionRepository;
import com.vyankatesh.careerrecommendationsystem.repository.UserRepository;
@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;
    
    private final UserRepository userRepository;

    private final QuestionRepository questionRepository;

    private final OptionRepository optionRepository;
   
    public StudentAnswerServiceImpl(StudentAnswerRepository studentAnswerRepository,
            UserRepository userRepository,
            QuestionRepository questionRepository,
            OptionRepository optionRepository) {

    	this.studentAnswerRepository = studentAnswerRepository;
    	this.userRepository = userRepository;
    	this.questionRepository = questionRepository;
    	this.optionRepository = optionRepository;
    }
    
    
    @Override
    public StudentAnswer saveStudentAnswer(StudentAnswer studentAnswer) {
        return studentAnswerRepository.save(studentAnswer);
    }

    @Override
    public void submitAssessment(List<StudentAnswerRequestDTO> answers) {

        for (StudentAnswerRequestDTO dto : answers) {

            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User not found with id: " + dto.getUserId()));

            Question question = questionRepository.findById(dto.getQuestionId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Question not found with id: " + dto.getQuestionId()));

            Option option = optionRepository.findById(dto.getOptionId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Option not found with id: " + dto.getOptionId()));

            StudentAnswer studentAnswer = new StudentAnswer();

            studentAnswer.setUser(user);
            studentAnswer.setQuestion(question);
            studentAnswer.setSelectedOption(option);

            studentAnswerRepository.save(studentAnswer);
        }

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