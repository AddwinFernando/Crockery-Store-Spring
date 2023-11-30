package com.restapi.service;

import com.restapi.dto.ItemDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Item;
import com.restapi.repository.ItemRepository;
import com.restapi.request.ItemRequest;
import com.restapi.request.StockRequest;
import com.restapi.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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

    @Transactional
    public ItemResponse addItem(ItemRequest itemRequest) {
        Item item = itemDto.mapToItem(itemRequest);
        itemRepository.save(item);
        return findAll();
    }

    @Transactional
    public ItemResponse updateItem(ItemRequest itemRequest) {
        Item item = itemDto.mapToItem(itemRequest);
        itemRepository.save(item);
        return findAll();
    }

    public ItemResponse deleteItem(Integer id) {
        itemRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public ItemResponse getItem(Integer id) {
        List<Item> item= itemRepository.findAll().stream().filter(item1 -> Objects.equals(item1.getId(), Long.valueOf(id))).toList();
        return itemDto.mapToItemResponse(item);
    }

    @Transactional
    public ItemResponse updateStock(List<StockRequest> stockRequest) {
        for(StockRequest stockItem:stockRequest){
            Item item = itemRepository.findAll().stream().filter(item1 -> Objects.equals(item1.getId(), stockItem.getId())).toList().get(0);
            item.setStock(item.getStock()- stockItem.getStock());
            itemRepository.save(item);
        }
        return findAll();
    }
}
