package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.repository.ItemRepository;
import com.restapi.request.ItemRequest;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RolesAllowed(Role.ADMIN)
@RequestMapping("/api/admin/item")
public class AdminItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private APIResponse apiResponse;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllItems(){
        ItemResponse itemResponse = itemService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addItem(@RequestBody ItemRequest itemRequest){
        ItemResponse itemResponse = itemService.addItem(itemRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateItem(@RequestBody ItemRequest itemRequest){
        ItemResponse itemResponse = itemService.updateItem(itemRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteItem(@PathVariable Integer id){
        ItemResponse itemResponse = itemService.deleteItem(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
