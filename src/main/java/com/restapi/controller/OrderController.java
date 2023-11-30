package com.restapi.controller;

import com.restapi.repository.OrderRepository;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    APIResponse apiResponse;


    @GetMapping("/get/{user}")
    public ResponseEntity<APIResponse> getUserOrder(@PathVariable Long user){
        List<OrderResponse> orderResponse = orderService.findUserOrders(user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponse);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/place")
    public ResponseEntity<APIResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        List<OrderResponse> orderResponses = orderService.placeOrder(orderRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponses);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
    }
}
