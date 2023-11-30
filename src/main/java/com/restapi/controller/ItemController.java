package com.restapi.controller;

import com.restapi.request.ItemRequest;
import com.restapi.request.StockRequest;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllItems(){
        ItemResponse itemResponse = itemService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/update-stock")
    public ResponseEntity<APIResponse> updateStock(@RequestBody List<StockRequest> stockRequest){
        ItemResponse itemResponse = itemService.updateStock(stockRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
