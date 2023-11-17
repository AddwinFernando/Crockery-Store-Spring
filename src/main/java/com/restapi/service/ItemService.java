package com.restapi.service;

import com.restapi.dto.ItemDto;
import com.restapi.model.Item;
import com.restapi.repository.ItemRepository;
import com.restapi.request.ItemRequest;
import com.restapi.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDto itemDto;
    public ItemResponse findAll() {
        List<Item> items = itemRepository.findAll();
        return itemDto.mapToItemResponse(items);
    }

    public ItemResponse addItem(ItemRequest itemRequest) {
        Item item = itemDto.mapToItem(itemRequest);
        itemRepository.save(item);
        return findAll();
    }

    public ItemResponse updateItem(ItemRequest itemRequest) {
        Item item = itemDto.mapToItem(itemRequest);
        itemRepository.save(item);
        return findAll();
    }

    public ItemResponse deleteItem(Integer id) {
        itemRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

}
