package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.request.StatusRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RolesAllowed(Role.ADMIN)
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    APIResponse apiResponse;


    @GetMapping("/get")
    public ResponseEntity<APIResponse> getallOrder() {
        List<OrderResponse> orderResponse = orderService.findALlOrders();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponse);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<APIResponse> setStatus(@RequestBody StatusRequest statusRequest){
        List<OrderResponse> orderResponse = orderService.setStatus(statusRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponse);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
    }
}
