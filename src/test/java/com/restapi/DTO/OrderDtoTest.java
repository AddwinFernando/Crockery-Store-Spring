package com.restapi.DTO;

import com.restapi.dto.OrderDto;
import com.restapi.model.*;
import com.restapi.repository.AddressRepository;
import com.restapi.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderDtoTest {
    @InjectMocks
    OrderDto orderDto;

    @Mock
    AddressRepository addressRepository;

    @Test
    public void mapToOrderResponse(){
        List<OrderedItem> orderedItems = new ArrayList<>();
        Role role = Role.builder().name("User").id(1).build();
        AppUser appUser = AppUser.builder().name("Test-Name").username("Test-User").password("Test").build();
        OrderStatus orderStatus = new OrderStatus("Placed");
        Address address = Address.builder().id(1L).address("Test-Address").city("Test-City").zipcode(600045).appUser(appUser).build();
        Order order = new Order(1L,orderedItems,appUser,orderStatus,address,null);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        OrderResponse orderResponse = new OrderResponse(1L,orderedItems,1L,"Test-User","Test-Name",address,orderStatus.getStatus());
        List<OrderResponse> orderResponses = new ArrayList<>();
        orderResponses.add(orderResponse);

        assertEquals(orderResponses.get(0).getName(),orderDto.mapToOrderResponse(orders).get(0).getUserName());
    }

}
