package com.restapi.dto;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.UserRepository;
import com.restapi.request.AddressRequest;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDto {
    @Autowired
    private UserRepository userRepository;
    public Address mapToAddress (AddressRequest addressRequest){
        Address address = new Address();
        if(addressRequest.getId()!= null){
            address.setId(addressRequest.getId());
        }
        address.setAddress(addressRequest.getAddress());
        address.setCity(addressRequest.getCity());
        address.setZipcode(addressRequest.getZipcode());
        AppUser user = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User","userId",addressRequest.getUserId()));
        address.setAppUser(user);
        return address;
    }
}
