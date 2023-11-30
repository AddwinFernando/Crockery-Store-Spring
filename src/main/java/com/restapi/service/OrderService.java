package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.OrderRequest;
import com.restapi.request.StatusRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    @Autowired
    private OrderDto orderDto;

    public List<OrderResponse> findALlOrders() {
        return orderDto.mapToOrderResponse(orderRepository.findAll());
    }

    public List<OrderResponse> findUserOrders(Long user) {
        return orderDto.mapToOrderResponse(orderRepository.findAll()
                .stream()
                .filter(order -> Objects.equals(order.getAppUser().getId(), user))
                .toList());
    }

    public List<OrderResponse> placeOrder(OrderRequest orderRequest) {
        AppUser appUser = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User","UserId",orderRequest.getUserId()));

        Address address = addressRepository.findById(orderRequest.getAddressId())
                .orElseThrow(()-> new ResourceNotFoundException("Address","AddressId",orderRequest.getAddressId()));

        OrderStatus status = orderStatusRepository.findById(1L)
                .orElseThrow(()-> new ResourceNotFoundException("Status","StatusId",1L));

        List<Cart> cartList = cartRepository.findUserCart(orderRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("Cart","UserId",orderRequest.getUserId()));

        Order order = new Order();
        order.setAppUser(appUser);
        order.setAddress(address);
        order.setOrderStatus(status);

        order = orderRepository.save(order);

        for(Cart cart:cartList){
            OrderedItem orderedItem = new OrderedItem();
            orderedItem.setOrder(order);
            orderedItem.setTitle(cart.getItem().getTitle());
            orderedItem.setDescription(cart.getItem().getDescription());
            orderedItem.setCount(cart.getCount());
            orderedItem.setPrice(cart.getItem().getPrice());
            orderedItemRepository.save(orderedItem);
            cartRepository.deleteById(cart.getId());
        }
        return findUserOrders(appUser.getId());

    }

    public List<OrderResponse> setStatus(StatusRequest statusRequest) {
        Order order = orderRepository.findById(Long.valueOf(statusRequest.getOrderId()))
                .orElseThrow(()->new ResourceNotFoundException("Item","ItemId", statusRequest.getOrderId()));
        OrderStatus orderStatus = orderStatusRepository.findById(Long.valueOf(statusRequest.getStatusId()))
                .orElseThrow(()->new ResourceNotFoundException("Item","ItemId", statusRequest.getStatusId()));
        order.setOrderStatus(orderStatus);

        orderRepository.save(order);

        return findALlOrders();
    }
}
