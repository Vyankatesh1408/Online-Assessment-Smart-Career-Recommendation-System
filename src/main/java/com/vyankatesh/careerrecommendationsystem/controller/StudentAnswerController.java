package com.vyankatesh.careerrecommendationsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.entity.StudentAnswer;
import com.vyankatesh.careerrecommendationsystem.service.StudentAnswerService;
import com.vyankatesh.careerrecommendationsystem.dto.request.StudentAnswerRequestDTO;

@RestController
@RequestMapping("/api/student-answers")
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentAnswer saveStudentAnswer(@RequestBody StudentAnswer studentAnswer) {
        return studentAnswerService.saveStudentAnswer(studentAnswer);
    }
    
    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.OK)
    public void submitAssessment(@RequestBody List<StudentAnswerRequestDTO> answers) {

        studentAnswerService.submitAssessment(answers);

    }

    @GetMapping("/{id}")
    public StudentAnswer getStudentAnswerById(@PathVariable Long id) {
        return studentAnswerService.getStudentAnswerById(id);
    }

    @GetMapping
    public List<StudentAnswer> getAllStudentAnswers() {
        return studentAnswerService.getAllStudentAnswers();
    }

    @GetMapping("/user/{userId}")
    public List<StudentAnswer> getStudentAnswersByUserId(@PathVariable Long userId) {
        return studentAnswerService.getStudentAnswersByUserId(userId);
    }

    @GetMapping("/assessment/{assessmentId}")
    public List<StudentAnswer> getStudentAnswersByAssessmentId(@PathVariable Long assessmentId) {
        return studentAnswerService.getStudentAnswersByAssessmentId(assessmentId);
    }

    @PutMapping("/{id}")
    public StudentAnswer updateStudentAnswer(@PathVariable Long id,
                                             @RequestBody StudentAnswer studentAnswer) {
        return studentAnswerService.updateStudentAnswer(id, studentAnswer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentAnswer(@PathVariable Long id) {
        studentAnswerService.deleteStudentAnswer(id);
    }
}