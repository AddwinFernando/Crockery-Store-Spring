package com.restapi.DTO;

import com.restapi.dto.CategoryDto;
import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CategoryCtoTest {
    @InjectMocks
    CategoryDto categoryDto;

    @Test
    public void mapToCategoryResponseTest(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1L,"Test-Category");
        categories.add(category);
        CategoryRequest categoryRequest = new CategoryRequest(1L,"Test-Category");
        List<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(categoryRequests);

        assertEquals(categoryResponse.getCategories().get(0).getTitle(),categoryDto.mapToCategoryResponse(categories).getCategories().get(0).getTitle());
    }

    @Test
    public void mapToCategory(){
        CategoryRequest categoryRequest = new CategoryRequest(1L,"Test-Category");
        Category category = new Category(1L,"Test-Category");
        assertEquals(category.getTitle(),categoryDto.mapToCategory(categoryRequest).getTitle());
    }
}
