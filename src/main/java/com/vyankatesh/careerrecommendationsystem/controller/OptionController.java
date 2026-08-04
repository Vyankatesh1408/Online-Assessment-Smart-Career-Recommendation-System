package com.vyankatesh.careerrecommendationsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.entity.Option;
import com.vyankatesh.careerrecommendationsystem.service.OptionService;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

   

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Option saveOption(@RequestBody Option option) {
        return optionService.saveOption(option);
    }

    @GetMapping("/{id}")
    public Option getOptionById(@PathVariable Long id) {
        return optionService.getOptionById(id);
    }

    @GetMapping
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/question/{questionId}")
    public List<Option> getOptionsByQuestionId(@PathVariable Long questionId) {
        return optionService.getOptionsByQuestionId(questionId);
    }

    @PutMapping("/{id}")
    public Option updateOption(@PathVariable Long id,
                               @RequestBody Option option) {
        return optionService.updateOption(id, option);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
    }
}