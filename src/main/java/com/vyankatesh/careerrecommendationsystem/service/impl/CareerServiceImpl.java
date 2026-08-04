package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Career;
import com.vyankatesh.careerrecommendationsystem.exception.DuplicateResourceException;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.CareerRepository;
import com.vyankatesh.careerrecommendationsystem.service.CareerService;

@Service
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;

    public CareerServiceImpl(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    @Override
    public Career saveCareer(Career career) {

        if (careerRepository.existsByCareerName(career.getCareerName())) {
            throw new DuplicateResourceException(
                    "Career '" + career.getCareerName() + "' already exists.");
        }

        return careerRepository.save(career);
    }

    @Override
    public Career getCareerById(Long id) {

        return careerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Career not found with id: " + id));
    }

    @Override
    public List<Career> getAllCareers() {

        return careerRepository.findAll();
    }

    @Override
    public Career updateCareer(Long id, Career career) {

        Career existingCareer = careerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Career not found with id: " + id));

        if (!existingCareer.getCareerName().equals(career.getCareerName())
                && careerRepository.existsByCareerName(career.getCareerName())) {

            throw new DuplicateResourceException(
                    "Career '" + career.getCareerName() + "' already exists.");
        }

        existingCareer.setCareerName(career.getCareerName());
        existingCareer.setDescription(career.getDescription());

        return careerRepository.save(existingCareer);
    }

    @Override
    public void deleteCareer(Long id) {

        Career existingCareer = careerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Career not found with id: " + id));

        careerRepository.delete(existingCareer);
    }
}