package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Question;

public interface QuestionService {

    Question saveQuestion(Question question);

    Question getQuestionById(Long id);

    List<Question> getAllQuestions();

    List<Question> getQuestionsByAssessmentId(Long assessmentId);

    Question updateQuestion(Long id, Question question);

    void deleteQuestion(Long id);

}