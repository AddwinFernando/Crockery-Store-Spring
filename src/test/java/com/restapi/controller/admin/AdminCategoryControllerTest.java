package com.restapi.controller.admin;

import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminCategoryController.class)
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class AdminCategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;


    @MockBean
    CategoryService categoryService;
    @Test
    public void getAllCategoryTest() throws Exception {
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(new CategoryRequest(Long.valueOf(1),"Testtittle"));
        categoryResponse.setCategories(categoryRequests);
        when(categoryService.findAll()).thenReturn(categoryResponse);
        RequestBuilder req = MockMvcRequestBuilders.get("/api/admin/category/all");
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{\"data\":[{\"id\":1,\"title\":\"Testtittle\"}]}",res.getResponse().getContentAsString(),false);

    }

    @Test
    public void createCategoryTest() throws Exception {
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(new CategoryRequest(Long.valueOf(1),"Test"));
        categoryResponse.setCategories(categoryRequests);
        CategoryRequest categoryRequest = new CategoryRequest("Test");
        when(categoryService.createCategory(Mockito.any(CategoryRequest.class))).thenReturn(categoryResponse);
        RequestBuilder req = MockMvcRequestBuilders.post("/api/admin/category/add").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Test\"}").with(csrf());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{\"data\":[{\"id\":1,\"title\":\"Test\"}]}",res.getResponse().getContentAsString(),false);
    }

    @Test
    public void updateCategoryTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.put("/api/admin/category/update").contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"title\":\"Test\"}").with(csrf());
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(new CategoryRequest(Long.valueOf(1),"Test"));
        categoryResponse.setCategories(categoryRequests);
        when(categoryService.updateCategory(Mockito.any(CategoryRequest.class))).thenReturn(categoryResponse);
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{\"data\":[{\"id\":1,\"title\":\"Test\"}]}",res.getResponse().getContentAsString(),false);
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(new CategoryRequest(Long.valueOf(1),"Test"));
        categoryResponse.setCategories(categoryRequests);
        when(categoryService.deleteCategory(1)).thenReturn(categoryResponse);
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/admin/category/delete/1").with(csrf());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{\"data\":[{\"id\":1,\"title\":\"Test\"}]}",res.getResponse().getContentAsString(),false);
    }

}
