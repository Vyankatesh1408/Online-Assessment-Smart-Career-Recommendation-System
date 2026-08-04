package com.vyankatesh.careerrecommendationsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.entity.Career;
import com.vyankatesh.careerrecommendationsystem.service.CareerService;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Career saveCareer(@RequestBody Career career) {
        return careerService.saveCareer(career);
    }

    @GetMapping("/{id}")
    public Career getCareerById(@PathVariable Long id) {
        return careerService.getCareerById(id);
    }

    @GetMapping
    public List<Career> getAllCareers() {
        return careerService.getAllCareers();
    }

    @PutMapping("/{id}")
    public Career updateCareer(@PathVariable Long id, @RequestBody Career career) {
        return careerService.updateCareer(id, career);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCareer(@PathVariable Long id) {
        careerService.deleteCareer(id);
    }
}