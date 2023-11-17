package com.restapi.controller;

import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
