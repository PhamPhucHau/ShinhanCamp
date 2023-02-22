package com.example.demo.service.serviceImpl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
     CategoryRepository categoryRepository;
    @Mock
    ModelMapper mapper;
    private CategoryDTO categoryDTO;
    private Category category;
    @InjectMocks
     CategoryServiceImpl categorySerivce;
    @BeforeEach
    void setUp() {
        category= new Category(null,"category","image");
        categoryDTO=new CategoryDTO();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCategories() {
        // given - precondition or setup
        Category category1=new Category(null,"1234","1234");


        given(categoryRepository.findAll()).willReturn(List.of(category1,category1));

        // when -  action or the behaviour that we are going test
        List<Category> categoryList = categorySerivce.getAllCategories();

        // then - verify the output
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);

    }

    @Test
    void addCategory() {

        Category addedcategory=new Category(null,"category","image");
        categorySerivce.addCategory(mapper.map(addedcategory, CategoryDTO.class));
        ArgumentCaptor<Category> categoryArgumentCaptor=ArgumentCaptor.forClass(Category.class);
        // Call repository de test thu book moi
        verify(categoryRepository).save(categoryArgumentCaptor.capture());

        Category captureCategory=categoryArgumentCaptor.getValue();
        assertEquals(captureCategory,mapper.map(addedcategory,CategoryDTO.class));

    }

    @Test
    void deleteCategory() {

        long categoryId=1L;
        willDoNothing().given(categoryRepository).deleteById(categoryId);

        //
        categorySerivce.deleteCategory(categoryId);
        verify(categoryRepository,times(1)).deleteById(categoryId);
    }

    @Test
    void getCategoryById() {

        // given
        given(categoryRepository.findById(1L)).willReturn(Optional.ofNullable(category));
        given(mapper.map(category,CategoryDTO.class)).willReturn(categoryDTO);
        categoryDTO.setCategoriesId(1L);
        categoryDTO.setCategoriesName("category123");
        categoryDTO.setImage("image1234");
        // when
        CategoryDTO findedcategoryDTO=categorySerivce.getCategoryById(categoryDTO.getCategoriesId());
        // then
        assertThat(findedcategoryDTO).isNotNull();
    }

    @Test
    void updateCategory() {

        // given
        given(categoryRepository.save(category)).willReturn(category);
        category.setCategoriesId(1L);
        category.setCategoriesName("category123");
        category.setImage("image1234");
        given(mapper.map(categoryDTO,Category.class)).willReturn(category);
        category.setCategoriesId(1L);
        category.setCategoriesName("category123");
        category.setImage("image1234");
        given(categoryRepository.findById(1L)).willReturn(Optional.ofNullable(category));
        // when
        categorySerivce.updateCategory(1L,categoryDTO);
       Category updatecategory= categoryRepository.findById(1L).get();

        // then
        assertThat(updatecategory.getCategoriesName()).isEqualTo("category123");
        assertThat(updatecategory.getImage()).isEqualTo("image1234");

    }
}