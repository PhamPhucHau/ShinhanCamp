package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {
     public List<Category> getAllCategories();
     public CategoryDTO addCategory(CategoryDTO category);
     public Boolean deleteCategory(long id);
     public CategoryDTO getCategoryById(long id);
     public Boolean updateCategory(long id, CategoryDTO categoryDTO);
}
