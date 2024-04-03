package com.restapi.DTO;

import com.restapi.dto.AddressDto;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.UserRepository;
import com.restapi.request.AddressRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressDtoTest {
    @InjectMocks
    AddressDto addressDto;

    @Mock
    UserRepository userRepository;

    @Test
    public void mapToAddressTest(){
        AppUser appUser = AppUser.builder().id(1L).username("Test").build();
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(appUser));
        AddressRequest addressRequest = AddressRequest.builder().id(1L).address("Test-Address").city("Test-City").zipcode(600045).userId(1L).build();
        Address address = Address.builder().id(1L).address("Test-Address").city("Test-City").zipcode(600045).appUser(appUser).build();

        assertEquals(address.getId(),addressDto.mapToAddress(addressRequest).getId());
    }
}
