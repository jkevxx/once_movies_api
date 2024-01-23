package com.example.once_movies_api.controller;

import com.example.once_movies_api.dto.CategoryDTO;
import com.example.once_movies_api.entity.CategoryEntity;
import com.example.once_movies_api.exception.ResourceNotFoundException;
import com.example.once_movies_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        try {
            return ResponseEntity.ok(categoryService.getCategories());
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategory(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/category")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Category created")
    public ResponseEntity<CategoryEntity> createCategory(@Valid @RequestBody CategoryEntity category) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(category));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/category/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Category updated")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryEntity category) {
        try {
            return ResponseEntity.ok(categoryService.updateCategory(id, category));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/category/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Category deleted successfully")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
