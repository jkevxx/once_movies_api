package com.example.once_movies_api.service;

import com.example.once_movies_api.dto.CategoryDTO;
import com.example.once_movies_api.entity.CategoryEntity;
import com.example.once_movies_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categories = categoryRepository.findAll().stream().map(this::convertToDTO).toList();

        if (categories.isEmpty()) {
            throw new RuntimeException("Categories not found");
        }

        return categories;
    }

    @Override
    public CategoryDTO getCategory(Long id) {

        CategoryEntity category = categoryRepository.findById(id).orElse(null);

        if(category == null) {
            throw new RuntimeException("Category not found");
        }

        return convertToDTO(category);
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity) {
        CategoryEntity categoryFound = categoryRepository.findById(id).orElse(null);

        if(categoryFound == null) {
            throw new RuntimeException("Category not found");
        }

        categoryFound.setTitle(categoryEntity.getTitle());

        return categoryRepository.save(categoryFound);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryEntity categoryFound = categoryRepository.findById(id).orElse(null);

        if(categoryFound == null) {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.deleteById(id);
    }

    private CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setTitle(categoryEntity.getTitle());
        return categoryDTO;
    }
}
