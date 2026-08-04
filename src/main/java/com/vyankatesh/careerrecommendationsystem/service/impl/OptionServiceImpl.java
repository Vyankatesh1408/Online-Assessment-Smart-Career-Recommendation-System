package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Option;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.OptionRepository;
import com.vyankatesh.careerrecommendationsystem.service.OptionService;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option getOptionById(Long id) {
        return optionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Option not found with id: " + id));
    }

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    @Override
    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionId(questionId);
    }

    @Override
    public Option updateOption(Long id, Option option) {

        Option existingOption = optionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Option not found with id: " + id));

        existingOption.setOptionText(option.getOptionText());
        existingOption.setIsCorrect(option.getIsCorrect());
        existingOption.setQuestion(option.getQuestion());

        return optionRepository.save(existingOption);
    }

    @Override
    public void deleteOption(Long id) {

        Option existingOption = optionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Option not found with id: " + id));

        optionRepository.delete(existingOption);
    }
}