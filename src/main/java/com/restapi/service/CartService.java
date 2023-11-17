package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Item;
import com.restapi.repository.CartRepository;
import com.restapi.repository.ItemRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;
    public List<Cart> getCart(Long userId) {
        List<Cart> cart = cartRepository.findUserCart(userId)
                .orElseThrow(()-> new ResourceNotFoundException("cart", "userId", userId));
        return cart;
    }

    public List<Cart> addToCart(CartRequest cartRequest) {
        Optional<List<Cart>> cartOptional = cartRepository.findUserCart(cartRequest.getUserId());

        AppUser user = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User","UserId",cartRequest.getUserId()));

        Item item = itemRepository.findById(cartRequest.getItemId())
                .orElseThrow(()->new ResourceNotFoundException("Item","ItemId",cartRequest.getItemId()));


        if(cartOptional.isPresent()){
            boolean isFound = false;
            for (Cart cart:cartOptional.get()){
                if(cart.getItem().getId().equals(cartRequest.getItemId())){
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                Cart cart = new Cart();
                cart.setAppUser(user);
                cart.setItem(item);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        } else {
            Cart cart = new Cart();
            cart.setAppUser(user);
            cart.setItem(item);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return getCart(user.getId());
    }

    public List<Cart> removeFromCart(Integer id,Integer user) {
        cartRepository.deleteById(Long.valueOf(id));
        return getCart(Long.valueOf(user));
    }
}
