package com.scarlet.task_manager_api.service;

import com.scarlet.task_manager_api.domain.Category;
import com.scarlet.task_manager_api.persistence.entity.Categoria;
import com.scarlet.task_manager_api.persistence.mapper.CategoryMapper;
import com.scarlet.task_manager_api.persistence.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(
            CategoryRepository categoryRepository,
            CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public List<Category> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDomain)
                .collect(Collectors.toList());
    }


    public Optional<Category> getCategoryById(Integer id) {

        return categoryRepository.findById(id)
                .map(categoryMapper::toDomain);
    }


    public Category createCategory(Category category) {

        Categoria entity = categoryMapper.toEntity(category);

        Categoria savedEntity = categoryRepository.save(entity);

        return categoryMapper.toDomain(savedEntity);
    }


    public Category updateCategory(Integer id, Category categoryDetails) {

        Categoria entity = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        entity.setName(categoryDetails.getName());

        Categoria updatedEntity = categoryRepository.save(entity);

        return categoryMapper.toDomain(updatedEntity);
    }


    public void deleteCategory(Integer id) {

        Categoria entity = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        categoryRepository.delete(entity);
    }

}