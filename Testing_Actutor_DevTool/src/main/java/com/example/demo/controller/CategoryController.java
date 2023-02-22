package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("")
@RequestMapping(value="/category")
public class CategoryController {
    @Autowired
    CategoryService CategoryService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getCategory()
    {
        return ResponseEntity.ok().body(CategoryService.getAllCategories());
    }
    @PostMapping("")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryService.addCategory(categoryDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getCategoryById(@PathVariable(name="id") long id)
    {
        CategoryDTO categoryDTO= CategoryService.getCategoryById(id);
        if(categoryDTO!=null)
        return ResponseEntity.ok().body(CategoryService.getCategoryById(id));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateCategory(@PathVariable(name="id") long id,@RequestBody CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok().body(CategoryService.updateCategory(id,categoryDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCategory(@PathVariable(name="id") long id,@RequestBody CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok().body(CategoryService.updateCategory(id,categoryDTO));
    }
}
