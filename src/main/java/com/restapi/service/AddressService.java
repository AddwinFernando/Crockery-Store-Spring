package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.model.Address;
import com.restapi.repository.AddressRepository;
import com.restapi.request.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressDto addressDto;
    public List<Address> findUserAddress(Long user) {
        return addressRepository.findAll().stream().filter(address -> Objects.equals(address.getAppUser().getId(), user)).toList();
    }

    public List<Address> addAddress(AddressRequest addressRequest) {
        Address address =addressDto.mapToAddress(addressRequest);
        addressRepository.save(address);
        return  findUserAddress(addressRequest.getUserId());
    }

    public List<Address> updateAddress(AddressRequest addressRequest) {
        Address address =addressDto.mapToAddress(addressRequest);
        addressRepository.save(address);
        return  findUserAddress(addressRequest.getUserId());
    }
}
