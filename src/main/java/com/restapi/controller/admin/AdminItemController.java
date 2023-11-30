package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.repository.ItemRepository;
import com.restapi.request.ItemRequest;
import com.restapi.response.ItemResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ItemService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;

@RestController
@RolesAllowed(Role.ADMIN)
@RequestMapping("/api/admin/item")
public class AdminItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllItems() {
        ItemResponse itemResponse = itemService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getItem(@PathVariable Integer id) {
        ItemResponse itemResponse = itemService.getItem(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addItem(@RequestParam("photo") MultipartFile file,
                                               @RequestParam("title") String title,
                                               @RequestParam("description") String description,
                                               @RequestParam("category") Long category,
                                               @RequestParam("stock") Integer stock,
                                               @RequestParam("price") Double price) {
        String photo = storageService.storeFile(file);
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setPrice(price);
        itemRequest.setPhoto(photo);
        itemRequest.setTitle(title);
        itemRequest.setDescription(description);
        itemRequest.setCategory(category);
        itemRequest.setStock(stock);
        ItemResponse itemResponse = itemService.addItem(itemRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateItem(@RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.updateItem(itemRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteItem(@PathVariable Integer id) {
        ItemResponse itemResponse = itemService.deleteItem(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(itemResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
