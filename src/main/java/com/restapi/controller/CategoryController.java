package com.restapi.controller;

import com.restapi.request.FilterRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import com.restapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemService itemService;

    @Autowired
    APIResponse apiResponse;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategory(){
        CategoryResponse categoryResponse = categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<APIResponse> getFilteredItems(@RequestBody FilterRequest params){
        ItemResponse itemResponse = itemService.filter(params);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse.getItems());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/sortdesc")
    public ResponseEntity<APIResponse> getSortedItemsDesc(){
        ItemResponse itemResponse = itemService.sortDsc();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse.getItems());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/sortasc")
    public ResponseEntity<APIResponse> getSortedItemsAsc(){
        ItemResponse itemResponse = itemService.sortAsc();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse.getItems());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/sortalph")
    public ResponseEntity<APIResponse> getSortedItemsAlph(){
        ItemResponse itemResponse = itemService.sortAlph();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse.getItems());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
