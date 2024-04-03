package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.OrderRequest;
import com.restapi.request.StatusRequest;
import com.restapi.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    AddressRepository addressRepository;
    @Mock
    OrderStatusRepository orderStatusRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    OrderedItemRepository orderedItemRepository;

    @Mock
    OrderDto orderDto;

    public List<Order> createOrder(){
        Order order = new Order();
        order.setId(1L);
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setName("Test-User");
        order.setAppUser(appUser);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1L);
        orderStatus.setStatus("Placed");
        order.setOrderStatus(orderStatus);
        Address address = new Address();
        address.setId(1L);
        order.setAddress(address);
        order.setOrderedItems(new ArrayList<>());
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }

    public List<OrderResponse> mapper(){
        List<OrderResponse> orderResponses = new ArrayList<>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(1L);
        orderResponse.setUserId(1L);
        orderResponse.setUserName("Test-User");
        orderResponse.setName("Test");
        orderResponse.setOrderStatus("Placed");
        Address address = new Address();
        address.setId(1L);
        orderResponse.setAddress(address);
        orderResponse.setOrderedItems(new ArrayList<>());
        orderResponses.add(orderResponse);
        return orderResponses;
    }

    @Test
    public void findALlOrdersTest() {
        when(orderRepository.findAll()).thenReturn(createOrder());
        when(orderDto.mapToOrderResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(1L,orderService.findALlOrders().get(0).getId());
    }

    @Test
    public void findUserOrdersTest() {
        when(orderRepository.findAll()).thenReturn(createOrder());
        when(orderDto.mapToOrderResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(1L,orderService.findUserOrders(1L).get(0).getId());
    }

    @Test
    public void placeOrderTest() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setName("Test_Name");
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(appUser));
        Address address = new Address();
        address.setId(1L);
        when(addressRepository.findById(Mockito.any())).thenReturn(Optional.of(address));
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1L);
        orderStatus.setStatus("placed");
        when(orderStatusRepository.findById(Mockito.any())).thenReturn(Optional.of(orderStatus));
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setAppUser(appUser);
        cart.setItem(new Item());
        cart.setCount(10);
        when(cartRepository.findUserCart(Mockito.any())).thenReturn(Optional.of(carts));
        when(orderService.findUserOrders(1L)).thenReturn(mapper());
        assertEquals(1L,orderService.placeOrder(new OrderRequest(1L,1L)).get(0).getId());
    }

    @Test
    public void setStatusTest(){
        Order order = createOrder().get(0);
        when(orderRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(order));
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1L);
        orderStatus.setStatus("placed");
        when(orderStatusRepository.findById(Mockito.any())).thenReturn(Optional.of(orderStatus));
        when(orderService.findALlOrders()).thenReturn(mapper());
        StatusRequest statusRequest = new StatusRequest();
        statusRequest.setStatusId(1);
        statusRequest.setOrderId(1);
        assertEquals(1L,orderService.setStatus(statusRequest).get(0).getId());
    }


}
