package com.restapi.controller;


import com.restapi.request.ItemRequest;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
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
@WebMvcTest(ItemController.class)
@WithMockUser(username = "user", password = "user", roles = "USER")
public class ItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    ItemService itemService;

    public ItemResponse mapper(){
        ItemResponse itemResponse = new ItemResponse();
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(new ItemRequest(Long.valueOf(1), "Test", "TestDesc", Double.valueOf(100), null, Long.valueOf(1), 10));
        itemResponse.setItems(itemRequests);
        return itemResponse;
    }

    @Test
    public void getAllItemsTes() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/item/all");
        when(itemService.findAll()).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:{items:[{id:1,title:Test,description:TestDesc,price:100.0,photo:null,category:1,stock:10}]}}", res.getResponse().getContentAsString(), false);
    }

//    @Test
//    public void updateStockTest() throws Exception {
//        RequestBuilder req = MockMvcRequestBuilders.put("/api/item/update-stock").contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"stock\":10}").with(csrf());
//        when(itemService.updateStock(Mockito.any())).thenReturn(mapper());
//        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
//        JSONAssert.assertEquals("{data:[{id:1,address:TestAddress,city:TestCity,zipcode:600045}]}", res.getResponse().getContentAsString(), false);
//    }
}
