package com.restapi.DTO;

import com.restapi.dto.AuthDto;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthDtoTest {
    @InjectMocks
    AuthDto authDto;

    @Test
    public void mapToAppUserTest(){
        RegisterRequest registerRequest = RegisterRequest.builder().name("Test-Name").password("Test").username("Test-User").build();
        AppUser appUser = AppUser.builder().name("Test-Name").username("Test-User").password("Test").build();
        assertEquals(appUser.getUsername(),authDto.mapToAppUser(registerRequest).getUsername());
    }

    @Test
    public  void  mapToAuthResponse(){
        Role role = Role.builder().name("User").id(1).build();
        AppUser appUser = AppUser.builder().name("Test-Name").username("Test-User").password("Test").roles(role).build();
        AuthResponse authResponse = AuthResponse.builder().id(1L).name("Test-Name").role("User").username("TestUser").build();
        assertEquals(authResponse.getName(),authDto.mapToAuthResponse(appUser).getName());
    }
}
