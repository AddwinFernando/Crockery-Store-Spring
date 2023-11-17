package com.restapi.controller;

import com.restapi.model.Cart;
import com.restapi.request.CartRequest;
import com.restapi.request.ItemRequest;
import com.restapi.response.CartResponse;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CartService cartService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<APIResponse> getUserCart(@PathVariable Long userId){
        List<Cart> cart = cartService.getCart(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cart);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addToCart(@RequestBody CartRequest cartRequest){
        List<Cart> cart = cartService.addToCart(cartRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cart);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/remove/{user}/{id}")
    public ResponseEntity<APIResponse> removeFromCart(@PathVariable Integer id,@PathVariable Integer user){
        List<Cart> cart = cartService.removeFromCart(id,user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cart);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}

