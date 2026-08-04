package com.vyankatesh.careerrecommendationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}