package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.AddressRepository;
import com.restapi.request.AddressRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;


    @Mock
    AddressRepository addressRepository;


    @Mock
    AddressDto addressDto;


    public List<Address> returnAddress(){
        Address address = new Address();
        AppUser appUser = new AppUser();
        appUser.setId(Long.valueOf(1));
        appUser.setName("TestUser");
        List<Address> Addresses = new ArrayList<>();
        address.setAppUser(appUser);
        address.setAddress("TestAddress");
        address.setId(Long.valueOf(1));
        address.setCity("Test City");
        address.setZipcode(600045);
        address.setCreatedAt(null);
        Addresses.add(address);
        return Addresses;
    }
    public Address mapper(){
        Address address = new Address();
        address.setId(Long.valueOf(1));
        address.setAddress("TestAddress");
        address.setCity("TestCity");
        address.setZipcode(600045);
        address.setAppUser(null);
        return address;
    }


    @Test
    public void findUserAddress(){
        //Arrage
        when(addressRepository.findAll()).thenReturn(returnAddress());

        //Act and Assert
        assertEquals(addressService.findUserAddress(Long.valueOf(1)).get(0).getId(),
                Long.valueOf(1));
        assertEquals(addressService.findUserAddress(Long.valueOf(1)).get(0).getAddress(),
                "TestAddress");
        assertEquals(addressService.findUserAddress(Long.valueOf(1)).get(0).getCity(),
                "Test City");
        assertEquals(addressService.findUserAddress(Long.valueOf(1)).get(0).getZipcode(),
                600045);

    }

    @Test
    public void addAddressTest(){
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setUserId(Long.valueOf(1));
        when(addressDto.mapToAddress(Mockito.any())).thenReturn(mapper());
        when(addressService.findUserAddress(Long.valueOf(1))).thenReturn(returnAddress());
        assertEquals(addressService.addAddress(addressRequest).get(0).getId(),Long.valueOf(1));
    }

    @Test
    public void updateAddressTest(){
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setUserId(Long.valueOf(1));
        when(addressDto.mapToAddress(Mockito.any())).thenReturn(mapper());
        when(addressService.findUserAddress(Long.valueOf(1))).thenReturn(returnAddress());
        assertEquals(addressService.updateAddress(addressRequest).get(0).getId(),Long.valueOf(1));
    }

    @Test
    public void deleteAddressTest(){
        when(addressService.findUserAddress(Long.valueOf(1))).thenReturn(returnAddress());
        assertEquals(addressService.delete(Long.valueOf(1),Long.valueOf(1)).get(0).getId(),Long.valueOf(1));
    }
}
