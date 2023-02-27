package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private  CategoryRepository categoryRepository;
    @BeforeEach
     void  setUp() {
            // Lam sao import het data vao day cung mot luc
    }
    @Test
    void testFindByCategoriesName()
    {
        categoryRepository.save(new Category(null,"category1","image"));
        Category findedCategory=categoryRepository.findByCategoriesName("category1");
        assertNotNull(findedCategory);
        assertEquals(findedCategory.getCategoriesName(),"category1");
        assertEquals(findedCategory.getImage(),"image");
    }
    @Test
    void saveCategoryTest()
    {

        Category category=new Category(null,"category2","image2");
        categoryRepository.save(category);
        assertThat(category.getCategoriesId()).isGreaterThan(0);
    }
    @Test
    void updateCategoryTest()
    {
        Category _category=categoryRepository.save(new Category(null,"category1","image"));
        Category category=categoryRepository.findById(_category.getCategoriesId()).get();
        category.setCategoriesName("category update");
        Category categoryUpdate=categoryRepository.save(category);
        assertThat(categoryUpdate.getCategoriesName()).isEqualTo("category update");
    }
    @Test
    void deleteCategoryTest()
    {
        Category _category=categoryRepository.save(new Category(null,"category1","image"));
        Category category=categoryRepository.findById(_category.getCategoriesId()).get();
        categoryRepository.delete(category);
        Category category1=null;
        Optional<Category> optionalCategory= Optional.ofNullable(categoryRepository.findByCategoriesName("category1"));
        if(optionalCategory.isPresent())
        {
            category1=optionalCategory.get();
        }
        assertThat(category1).isNull();
    }
    @Test
    void getListOfCategoryTest()
    {
        Category _category=categoryRepository.save(new Category(null,"category1","image"));
        Category category=categoryRepository.save(new Category(null,"category1","image"));
        List<Category> categoryList=categoryRepository.findAll();
        assertThat(categoryList.size()).isGreaterThan(0);
    }
    @Test
    void getCategoryTest()
    {
        Category _category=categoryRepository.save(new Category(null,"category1","image"));
        Category category=categoryRepository.findById(_category.getCategoriesId()).get();
        assertThat(category.getCategoriesId()).isEqualTo(_category.getCategoriesId());
    }
    @AfterEach
    void tearDown()
    {
        categoryRepository.deleteAll();
    }

}