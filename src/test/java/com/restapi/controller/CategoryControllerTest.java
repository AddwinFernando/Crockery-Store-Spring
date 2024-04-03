package com.restapi.controller;

import com.restapi.model.Category;
import com.restapi.model.Item;
import com.restapi.request.CategoryRequest;
import com.restapi.request.ItemRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import com.restapi.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
@WithMockUser(username = "user", password = "user", roles = "USER")
public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    ItemService itemService;

    @MockBean
    CategoryService categoryService;

    public CategoryResponse mapper(){
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        categoryRequests.add(new CategoryRequest(Long.valueOf(1),"Test"));
        categoryResponse.setCategories(categoryRequests);
        return categoryResponse;
    }

    public ItemResponse itemMapper(){
        ItemResponse itemResponse = new ItemResponse();
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(new ItemRequest(Long.valueOf(1), "Test", "TestDesc", Double.valueOf(100), null, Long.valueOf(1), 10));
        itemResponse.setItems(itemRequests);
        return itemResponse;
    }

    @Test
    public void getCategory() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/category/all");
        when(categoryService.findAll()).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,title:Test}]}", res.getResponse().getContentAsString(), false);
    }

//    @Test
//    public void getFilteredItemsTest() throws Exception {
//        RequestBuilder req = MockMvcRequestBuilders.post("/api/category/filter").contentType(MediaType.APPLICATION_JSON).content("{\"maxPice\":100.0,\"minPrice\":200.0,\"category\": \"Test\"}").with(csrf());
//        when(itemService.filter(Mockito.any())).thenReturn(itemMapper());
//        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
//        JSONAssert.assertEquals("{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}", res.getResponse().getContentAsString(), false);
//    }

    @Test
    public void getSortedItemsDescTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/category/sortdesc");
        when(itemService.sortDsc()).thenReturn(itemMapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}", res.getResponse().getContentAsString(), false);
    }

    @Test
    public void getSortedItemsAscTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/category/sortasc");
        when(itemService.sortAsc()).thenReturn(itemMapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}", res.getResponse().getContentAsString(), false);
    }

    @Test
    public void getSortedAlphTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/category/sortalph");
        when(itemService.sortAlph()).thenReturn(itemMapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}", res.getResponse().getContentAsString(), false);
    }


}
