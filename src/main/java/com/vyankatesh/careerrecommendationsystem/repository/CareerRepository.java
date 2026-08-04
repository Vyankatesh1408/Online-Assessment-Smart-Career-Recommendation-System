package com.vyankatesh.careerrecommendationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.Career;

public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findByCareerName(String careerName);

    boolean existsByCareerName(String careerName);

}