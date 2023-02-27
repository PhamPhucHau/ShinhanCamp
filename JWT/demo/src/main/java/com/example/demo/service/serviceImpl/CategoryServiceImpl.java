package com.example.demo.service.serviceImpl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    public List<Category> getAllCategories()
    {
        return (categoryRepository.findAll());
    }
    public CategoryDTO addCategory(CategoryDTO categoryDTO)
    {
        return mapper.map(categoryRepository.save(mapper.map(categoryDTO,Category.class)), CategoryDTO.class);
    }

    public Boolean deleteCategory(long id)
    {
        try{
            categoryRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public CategoryDTO getCategoryById(long id)
    {
        return mapper.map(categoryRepository.findById(id).orElse(null), CategoryDTO.class);
    }
    public Boolean updateCategory(long id, CategoryDTO categoryDTO)
    {
        Category oldCategory=categoryRepository.findById(id).orElse(null);
        if(oldCategory==null)
        {
            return false;
        }
        else
        {
            categoryRepository.save(mapper.map(categoryDTO,Category.class));
        }
        return true;
    }
}
