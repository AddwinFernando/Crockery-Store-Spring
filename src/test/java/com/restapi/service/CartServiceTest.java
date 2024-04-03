package com.restapi.service;

import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Item;
import com.restapi.repository.CartRepository;
import com.restapi.repository.ItemRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @InjectMocks
    CartService cartService;

    @Mock
    CartRepository cartRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ItemRepository itemRepository;

    public List<Cart> returnExistingCart(){
        Cart cart = new Cart();
        AppUser appUser = new AppUser();
        appUser.setId(Long.valueOf(1));
        appUser.setName("TestUser");
        Item item = new Item();
        item.setId(Long.valueOf(1));
        item.setTitle("TestItem");
        List<Cart> carts = new ArrayList<>();
        cart.setAppUser(appUser);
        cart.setItem(item);
        cart.setCount(1);
        cart.setId(Long.valueOf(1));
        carts.add(cart);
        return carts;
    }


    @Test
    public void testGetCart(){
        when(cartRepository.findUserCart(1L)).thenReturn(Optional.ofNullable(returnExistingCart()));
        assertEquals(1, cartService.getCart(1L).size());
        assertEquals("TestUser", cartService.getCart(1L).get(0).getAppUser().getName());
        assertEquals("TestItem", cartService.getCart(1L).get(0).getItem().getTitle());
        assertEquals(1, cartService.getCart(1L).get(0).getCount());
        assertEquals(1, cartService.getCart(1L).get(0).getId());
    }

    @Test
    public void addToCartTest(){
        AppUser user = new AppUser();
        user.setId(1L);
        Item item = new Item();
        item.setId(1L);
        when(cartRepository.findUserCart(1L)).thenReturn(Optional.ofNullable(returnExistingCart()));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        assertEquals(1, cartService.addToCart(new CartRequest(1L, 1L, 1)).size());
        assertEquals(1L,cartService.addToCart(new CartRequest(1L,1L,1)).get(0).getId());
        assertEquals(1L,cartService.addToCart(new CartRequest(1L,1L,1)).get(0).getAppUser().getId());
        assertEquals(1L,cartService.addToCart(new CartRequest(1L,1L,1)).get(0).getItem().getId());
    }

    @Test
    public void addNewItemToCartTest(){
        AppUser user = new AppUser();
        user.setId(1L);
        Item item = new Item();
        item.setId(2L);
        when(cartRepository.findUserCart(1L)).thenReturn(Optional.ofNullable(returnExistingCart()));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(itemRepository.findById(2L)).thenReturn(Optional.of(item));

        assertEquals(1, cartService.addToCart(new CartRequest(1L, 2L, 1)).size());
        assertEquals(1L,cartService.addToCart(new CartRequest(1L,2L,1)).get(0).getId());
        assertEquals(1L,cartService.addToCart(new CartRequest(1L,2L,1)).get(0).getAppUser().getId());
        assertEquals(1L,cartService.addToCart(new CartRequest(1L,2L,1)).get(0).getItem().getId());
    }

    @Test
    public void createCartTest(){
        AppUser user = new AppUser();
        user.setId(2L);
        Item item = new Item();
        item.setId(1L);
        when(cartRepository.findUserCart(2L)).thenReturn(Optional.ofNullable(returnExistingCart()));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        assertEquals(1, cartService.addToCart(new CartRequest(2L, 1L, 1)).size());
        assertEquals(1L,cartService.addToCart(new CartRequest(2L,1L,1)).get(0).getId());
        assertEquals(1L,cartService.addToCart(new CartRequest(2L,1L,1)).get(0).getAppUser().getId());
        assertEquals(1L,cartService.addToCart(new CartRequest(2L,1L,1)).get(0).getItem().getId());
    }
}
