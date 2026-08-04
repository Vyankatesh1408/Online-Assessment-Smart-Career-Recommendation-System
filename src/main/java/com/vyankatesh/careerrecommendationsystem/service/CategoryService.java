package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Category;

public interface CategoryService {

    Category saveCategory(Category category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}