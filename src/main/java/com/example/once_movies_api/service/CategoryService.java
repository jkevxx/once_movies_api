package com.example.once_movies_api.service;

import com.example.once_movies_api.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public List<CategoryEntity> getCategories();

    public CategoryEntity getCategory(Long id);

    public CategoryEntity createCategory(CategoryEntity categoryEntity);

    public CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity);

    public void deleteCategory(Long id);
}
