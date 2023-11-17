package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RolesAllowed(Role.ADMIN)
@RequestMapping("/api/admin/order")
public class AdminOrderController {
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private APIResponse apiResponse;
//    @GetMapping("/all")
//    public ResponseEntity<APIResponse> getAllOrders(){
//        OrderResponse orderResponse = orderService.findAll();
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(categoryResponse.getCategories());
//        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
//    }
}
