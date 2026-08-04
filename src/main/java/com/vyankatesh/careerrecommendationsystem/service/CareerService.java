package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Career;

public interface CareerService {

    Career saveCareer(Career career);

    Career getCareerById(Long id);

    List<Career> getAllCareers();

    Career updateCareer(Long id, Career career);

    void deleteCareer(Long id);
}