package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Category;
import com.vyankatesh.careerrecommendationsystem.exception.DuplicateResourceException;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.CategoryRepository;
import com.vyankatesh.careerrecommendationsystem.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {

        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new DuplicateResourceException(
                    "Category '" + category.getCategoryName() + "' already exists.");
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category category) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));

        if (!existingCategory.getCategoryName().equals(category.getCategoryName())
                && categoryRepository.existsByCategoryName(category.getCategoryName())) {

            throw new DuplicateResourceException(
                    "Category '" + category.getCategoryName() + "' already exists.");
        }

        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));

        categoryRepository.delete(existingCategory);
    }
}