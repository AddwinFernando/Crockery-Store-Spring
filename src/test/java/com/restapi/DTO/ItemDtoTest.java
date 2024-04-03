package com.restapi.DTO;

import com.restapi.dto.ItemDto;
import com.restapi.model.Category;
import com.restapi.model.Item;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.ItemRequest;
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
public class ItemDtoTest {
    @InjectMocks
    ItemDto itemDto;

    @Mock
    CategoryRepository categoryRepository;

    @Test
    public void mapToItemResponse(){
        Category category = new Category(1L,"Test-Category");
        Item item = new Item(1L,"Test-Tittle","Test-Description",100.0,10,null,null,category,null,null);
        List<Item> items = new ArrayList<>();
        items.add(item);

        ItemRequest itemRequest = new ItemRequest("Test-Tittle","Test-Description",100.0,null,category.getId());
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(itemRequest);
        ItemResponse itemResponse = new ItemResponse(itemRequests);

        assertEquals(itemResponse.getItems().get(0).getTitle(),itemDto.mapToItemResponse(items).getItems().get(0).getTitle());
    }

    @Test
    public void maptoItemTest(){
        Category category = new Category(1L,"Test-Category");
        ItemRequest itemRequest = new ItemRequest("Test-Tittle","Test-Description",100.0,null,category.getId());
        Item item = new Item(1L,"Test-Tittle","Test-Description",100.0,10,null,null,category,null,null);
        when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));

        assertEquals(item.getTitle(),itemDto.mapToItem(itemRequest).getTitle());

    }
}
