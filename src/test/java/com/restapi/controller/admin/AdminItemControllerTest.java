package com.restapi.controller.admin;

import com.restapi.model.Item;
import com.restapi.request.CategoryRequest;
import com.restapi.request.ItemRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ItemService;
import com.restapi.service.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminItemController.class)
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class AdminItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @MockBean
    StorageService storageService;

    @SpyBean
    APIResponse apiResponse;

    public ItemResponse mapper(){
        ItemResponse itemResponse = new ItemResponse();
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(new ItemRequest(Long.valueOf(1), "Test", "TestDesc", Double.valueOf(100), null, Long.valueOf(1), 10));
        itemResponse.setItems(itemRequests);
        return itemResponse;
    }


    @Test
    public void getALLItemsTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/admin/item/all");
        when(itemService.findAll()).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(    "{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}",res.getResponse().getContentAsString(),false);
    }

    @Test
    public void getItem() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/admin/item/1");
        when(itemService.getItem(Mockito.anyInt())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals( "{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}",res.getResponse().getContentAsString(),false);
    }

//    @Test
//    public  void addItemTest() throws Exception {
//        RequestBuilder req = MockMvcRequestBuilders.post("/api/admin/item/add").contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"title\":\"Test\",\"description\":\"TestDesc\",\"price\":100.0,\"photo\":null,\"category\":1,\"stock\":10}").with(csrf());
//        when(storageService.storeFile(Mockito.any(MultipartFile.class))).thenReturn("photo");
//        when(itemService.addItem(Mockito.any(ItemRequest.class))).thenReturn(mapper());
//        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
//        JSONAssert.assertEquals( "{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}",res.getResponse().getContentAsString(),false);
//
//    }
    @Test
    public void updateItemTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.put("/api/admin/item/update").contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"title\":\"Test\",\"description\":\"TestDesc\",\"price\":100.0,\"photo\":null,\"category\":1,\"stock\":10}").with(csrf());
        when(itemService.updateItem(Mockito.any(ItemRequest.class))).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals( "{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}",res.getResponse().getContentAsString(),false);
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        when(itemService.deleteItem(Mockito.anyInt())).thenReturn(mapper());
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/admin/item/delete/1").with(csrf());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}",res.getResponse().getContentAsString(),false);
    }


}


