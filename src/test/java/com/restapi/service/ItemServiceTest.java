package com.restapi.service;

import com.restapi.dto.ItemDto;
import com.restapi.model.Category;
import com.restapi.model.Item;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.ItemRepository;
import com.restapi.request.FilterRequest;
import com.restapi.request.ItemRequest;
import com.restapi.request.StockRequest;
import com.restapi.response.ItemResponse;
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
public class ItemServiceTest {
    @InjectMocks
    ItemService itemService;

    @Mock
    ItemDto itemDto;

    @Mock
    ItemRepository itemRepository;

    @Mock
    CategoryRepository categoryRepository;

    public List<Item> returnItems(){
        Item item = new Item();
        item.setId(1L);
        item.setTitle("TestTittle");
        item.setPrice(100.0);
        List<Item> items = new ArrayList<>();
        items.add(item);
        return items;
    }

    public ItemResponse mapper(){
        ItemResponse itemResponse = new ItemResponse();
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(new ItemRequest(1L, "TestTittle", "TestDesc", 100.0, null, 1L, 10));
        itemResponse.setItems(itemRequests);
        return itemResponse;
    }

    @Test
    public void getAllItemsTest(){
        when(itemRepository.findAll()).thenReturn(returnItems());
        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(1L,itemService.findAll().getItems().get(0).getId());
    }

    @Test
    public  void addItemTest(){
        when(itemDto.mapToItem(Mockito.any())).thenReturn(returnItems().get(0));
        when(itemService.findAll()).thenReturn(mapper());
        assertEquals(1L,itemService.addItem(new ItemRequest()).getItems().get(0).getId());
    }

    @Test
    public  void updateItemTest(){
        when(itemDto.mapToItem(Mockito.any())).thenReturn(returnItems().get(0));
        when(itemService.findAll()).thenReturn(mapper());
        assertEquals(1L,itemService.updateItem(new ItemRequest()).getItems().get(0).getId());
    }

    @Test
    public void deleteItem(){
        when(itemService.findAll()).thenReturn(mapper());
        assertEquals(1L,itemService.deleteItem(1).getItems().get(0).getId());
    }

//    @Test
//    public void getItemTest(){
//        List<Item> items = returnItems();
//        Item item = new Item();
//        item.setId(2L);
//        item.setTitle("TestTittle-2");
//        item.setPrice(200.0);
//        items.add(item);
//        when(itemRepository.findAll()).thenReturn(items);
//        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
//        assertEquals(2L,itemService.getItem(2).getItems().get(0).getId());
//
//    }

    @Test
    public void filterItemsTestWithCategory(){
        when(categoryRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(new Category(1L,"Test-Category")));
        when(itemRepository.findByPriceBetweenAndCategory(Mockito.any(),Mockito.any(),Mockito.any(Category.class)))
                .thenReturn(returnItems());
        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
        FilterRequest filterRequest = new FilterRequest(100.0,1000.0,1L);
        assertEquals(1L,itemService.filter(filterRequest).getItems().get(0).getId());
    }

    @Test
    public void filterItemsTestWithoutCategory(){
        when(itemRepository.findByPriceBetween(Mockito.any(),Mockito.any())).thenReturn(returnItems());
        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
        FilterRequest filterRequest = new FilterRequest(100.0,1000.0,-1L);
        assertEquals(1L,itemService.filter(filterRequest).getItems().get(0).getId());

    }

    @Test
    public void sortDscItem(){
        when(itemRepository.findAllByOrderByPriceDesc()).thenReturn(returnItems());
        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(1L,itemService.sortDsc().getItems().get(0).getId());
    }
    @Test
    public void sortAscItem(){
        when(itemRepository.findAllByOrderByPriceAsc()).thenReturn(returnItems());
        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(1L,itemService.sortAsc().getItems().get(0).getId());
    }

    @Test
    public void sortAlphItem(){
        when(itemRepository.findAllByOrderByTitleAsc()).thenReturn(returnItems());
        when(itemDto.mapToItemResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(1L,itemService.sortAlph().getItems().get(0).getId());
    }






}
