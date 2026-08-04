package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Option;

public interface OptionService {

    Option saveOption(Option option);

    Option getOptionById(Long id);

    List<Option> getAllOptions();

    List<Option> getOptionsByQuestionId(Long questionId);

    Option updateOption(Long id, Option option);

    void deleteOption(Long id);

}