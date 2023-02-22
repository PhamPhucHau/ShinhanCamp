package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCategory() throws Exception {
        // given
        List<Category> listOfCategories= new ArrayList<>();
        listOfCategories.add(new Category(1L,"category1","image"));
        listOfCategories.add(new Category(2L,"category1","image"));
        listOfCategories.add(new Category(3L,"category1","image"));
        listOfCategories.add(new Category(4L,"category1","image"));
        given(categoryService.getAllCategories()).willReturn(listOfCategories);
        // when
        ResultActions response=mockMvc.perform(get("/category"));
        // then
        response.andExpect(status().isOk()).andDo(print()).andExpect( jsonPath("$.size()",is(listOfCategories.size())));
    }

    @Test
    void addCategory() {
        // given - precondition or setup
        CategoryDTO categoryDTO=new CategoryDTO(1L,"category name", "image");
        given(categoryService.addCategory(categoryDTO)).willReturn((categoryDTO));
        // when
        ResultActions response= null;
        try {
            response = mockMvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(categoryDTO)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Then
        try {
            response.andExpect(status().isCreated()).andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getCategoryByIdReturnObject() throws Exception {
        // given - precondition or setup
        long categoryID=1L;
        CategoryDTO categoryDTO=new CategoryDTO(1L,"category name", "image");
        given(categoryService.getCategoryById(categoryID)).willReturn((categoryDTO));
        // when
        ResultActions response=mockMvc.perform(get("/category/{id}",categoryID));
        // Then
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.categoriesName",is(categoryDTO.getCategoriesName()))).andExpect(jsonPath("$.image",is(categoryDTO.getImage())));
    }
    @Test
    void getCategoryByIdReturnEmpty() throws Exception {
        // given - precondition or setup
        long categoryID=1L;
        CategoryDTO categoryDTO=new CategoryDTO(1L,"category name", "image");
        given(categoryService.getCategoryById(categoryID)).willReturn((null));
        // when
        ResultActions response=mockMvc.perform(get("/category/{id}",categoryID));
        // Then
        response.andExpect(status().isNotFound());
    }
    @Test
    void updateCategory() {
        // given - precondition or setup
        long categoryID=1L;
        CategoryDTO savedcategoryDTO=new CategoryDTO(1L,"category name", "image");
        CategoryDTO updatedcategoryDTO=new CategoryDTO(3L,"category name update", "image");
        given(categoryService.getCategoryById(categoryID)).willReturn((savedcategoryDTO));
        given(categoryService.updateCategory(categoryID,updatedcategoryDTO)).willAnswer((invocation)->invocation.getArgument(0));
        // when
        ResultActions response= null;
        try {
            response = mockMvc.perform(put("/category/{id}",categoryID).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedcategoryDTO)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Then
        try {
            response.andExpect(status().isOk()).andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void deleteCategory() throws Exception {
        // given
        long categoryID=1L;
        CategoryDTO deletedcategoryDTO=new CategoryDTO(1L,"category name", "image");
       given(categoryService.deleteCategory(categoryID)).willReturn(true);
        // when
        ResultActions response=mockMvc.perform(delete("/category/{id}",categoryID).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(deletedcategoryDTO)));;
        // then
        response.andExpect(status().isOk()).andDo(print());
    }
}