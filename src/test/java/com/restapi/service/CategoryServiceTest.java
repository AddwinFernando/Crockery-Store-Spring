package com.restapi.service;

import com.restapi.dto.CategoryDto;
import com.restapi.model.Category;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryDto categoryDto;

    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1L,"TestCategory");
        categories.add(category);
        return categories;
    }

    public CategoryResponse mapper(){
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(new CategoryRequest(1L,"TestCategory"));
        categoryResponse.setCategories(categoryRequests);
        return categoryResponse;
    }

    @Test
    public  void  findAllTest(){
        when(categoryRepository.findAll()).thenReturn(getCategories());
        when(categoryDto.mapToCategoryResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(Long.valueOf(1),categoryService.findAll().getCategories().get(0).getId());
    }

    @Test
    public void createCategoryTest(){
        when(categoryDto.mapToCategory(Mockito.any())).thenReturn(new Category(1L,"TestCategory"));
        when(categoryDto.mapToCategoryResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(Long.valueOf(1),categoryService.createCategory(new CategoryRequest(1L,"TestCategory")).getCategories().get(0).getId());
    }

    @Test
    public void updateCategoryTest(){
        when(categoryDto.mapToCategory(Mockito.any())).thenReturn(new Category(1L,"TestCategory"));
        when(categoryService.findAll()).thenReturn(mapper());
        assertEquals(Long.valueOf(1),categoryService.updateCategory(new CategoryRequest(1L,"TestCategory")).getCategories().get(0).getId());
    }

    @Test
    public void deleteCategoryTest(){
        when(categoryRepository.findAll()).thenReturn(getCategories());
        when(categoryService.findAll()).thenReturn(mapper());
        assertEquals(Long.valueOf(1),categoryService.deleteCategory(1).getCategories().get(0).getId());
    }

}
