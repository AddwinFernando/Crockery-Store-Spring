package com.restapi.controller;

import com.restapi.model.Cart;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
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
@WebMvcTest(CartController.class)
@WithMockUser(username = "user", password = "user", roles = "USER")
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    CartService cartService;

    public List<Cart> mapper(){
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(Long.valueOf(1));
        cart.setAppUser(null);
        cart.setCount(5);
        cart.setItem(null);
        cart.setCreatedAt(null);
        carts.add(cart);
        return carts;
    }

    @Test
    public void getCartTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/cart/get/1");
        when(cartService.getCart(Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,item:null,count:5}]}", res.getResponse().getContentAsString(), false);
    }

    @Test
    public void addToCartTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.post("/api/cart/add").contentType(MediaType.APPLICATION_JSON).content("{\"userId\":3,\"itemId\":5,\"count\":1}").with(csrf());
        when(cartService.addToCart(Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,item:null,count:5}]}", res.getResponse().getContentAsString(), false);
    }

    @Test
    public void removeFromCart() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/cart/remove/3/8").with(csrf());
        when(cartService.removeFromCart(Mockito.any(),Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,item:null,count:5}]}", res.getResponse().getContentAsString(), false);
    }

}
