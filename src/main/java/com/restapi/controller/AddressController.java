package com.restapi.controller;

import com.restapi.model.Address;
import com.restapi.request.AddressRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private APIResponse apiResponse;
    @GetMapping("/{user}")
    public ResponseEntity<APIResponse> getUserAdresses(@PathVariable Long user){
        List<Address> userAddresses = addressService.findUserAddress(user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userAddresses);
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addAddress(@RequestBody AddressRequest addressRequest){
        List<Address> userAddresses = addressService.addAddress(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userAddresses);
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateAddress(@RequestBody AddressRequest addressRequest){
        List<Address> userAddresses = addressService.updateAddress(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userAddresses);
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }

    @DeleteMapping("/{user}/{id}")
    public ResponseEntity<APIResponse> deleteAddress(@PathVariable Long user,@PathVariable Long id){
        List<Address> userAddresses = addressService.delete(user,id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userAddresses);
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }
}
