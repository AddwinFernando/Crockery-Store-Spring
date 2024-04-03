package com.restapi.controller;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressController.class)
@WithMockUser(username = "user", password = "user", roles = "USER")
public class addressControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    AddressService addressService;

    public List<Address> mapper(){
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setId(Long.valueOf(1));
        address.setAddress("TestAddress");
        address.setCity("TestCity");
        address.setZipcode(600045);
        address.setAppUser(null);
        addresses.add(address);
        return addresses;
    }


    @Test
    public void  getAllAddressTest() throws Exception {
        //Arrange
        RequestBuilder req = MockMvcRequestBuilders.get("/api/address/1");
        when(addressService.findUserAddress(Mockito.any())).thenReturn(mapper());
        //Act
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        //Assert
        JSONAssert.assertEquals(
                "{data:[{id:1,address:TestAddress,city:TestCity,zipcode:600045}]}"
                , res.getResponse().getContentAsString(), false);
    }

    @Test
    public void addAddressTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.post("/api/address/add").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"Street-1\",\"city\":\"City-1\",\"zipcode\":600045,\"userId\":3}").with(csrf());
        when(addressService.addAddress(Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,address:TestAddress,city:TestCity,zipcode:600045}]}", res.getResponse().getContentAsString(), false);
    }
    @Test
    public  void updateAddressTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.put("/api/address/update").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"Street-1\",\"city\":\"City-1\",\"zipcode\":600045,\"userId\":3}").with(csrf());
        when(addressService.updateAddress(Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,address:TestAddress,city:TestCity,zipcode:600045}]}", res.getResponse().getContentAsString(), false);
    }

    @Test
    public void deleteAddressTest() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/address/1/1").with(csrf());
        when(addressService.delete(Mockito.any(),Mockito.any())).thenReturn(mapper());
        MvcResult res = mockMvc.perform(req).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals("{data:[{id:1,address:TestAddress,city:TestCity,zipcode:600045}]}", res.getResponse().getContentAsString(), false);
    }
}
