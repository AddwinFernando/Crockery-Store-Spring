package com.restapi.controller.admin;

import com.restapi.model.OrderedItem;
import com.restapi.request.ItemRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminOrderController.class)
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class AdminOrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    OrderService orderService;

    public List<OrderResponse> mapper() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(Long.valueOf(1));
        orderResponse.setUserId(Long.valueOf(1));
        orderResponse.setUserName("TestUserName");
        orderResponse.setName("TestName");
        orderResponse.setOrderStatus("TestStatus");
        orderResponse.setAddress(null);
        orderResponse.setOrderedItems(null);
        orderResponses.add(orderResponse);
        return orderResponses;
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/admin/orders/get");
        when(orderService.findALlOrders()).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id: 1, orderedItems: null ,userId: 1, userName: TestUserName, name: TestName, address: null, orderStatus: TestStatus}]}", res.getResponse().getContentAsString(), false);
    }
    @Test
    public void updateItemTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.post("/api/admin/orders/status").contentType(MediaType.APPLICATION_JSON).content("{\"orderId\":1,\"statusId\":2}").with(csrf());
        when(orderService.setStatus(Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals( "{data:[{id: 1, orderedItems: null ,userId: 1, userName: TestUserName, name: TestName, address: null, orderStatus: TestStatus}]}",res.getResponse().getContentAsString(),false);
    }

}