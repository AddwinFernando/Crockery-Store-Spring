package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.model.Order;
import com.restapi.repository.AddressRepository;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {
    @Autowired
    private AddressRepository addressRepository;
    public List<OrderResponse> mapToOrderResponse(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order:orders){
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setUserId(order.getAppUser().getId());
            orderResponse.setUserName(order.getAppUser().getName());
            orderResponse.setName(order.getAppUser().getName());
            orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
            orderResponse.setAddress(order.getAddress());
            orderResponse.setOrderedItems(order.getOrderedItems());
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
}
