package com.example.once_movies_api.service;

import com.example.once_movies_api.dto.CategoryDTO;
import com.example.once_movies_api.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO getCategory(Long id);

    CategoryEntity createCategory(CategoryEntity categoryEntity);

    CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity);

    void deleteCategory(Long id);
}
