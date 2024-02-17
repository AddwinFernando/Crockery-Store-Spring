package com.restapi.service;

import com.restapi.dto.ItemDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Item;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.ItemRepository;
import com.restapi.request.FilterRequest;
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
    private CategoryRepository categoryRepository;

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

    public ItemResponse filter(FilterRequest params) {
        if(params.getCategory()>=0){
            Category category = categoryRepository.findById(params.getCategory())
                    .orElseThrow( ()-> new ResourceNotFoundException("category","categoryId", params.getCategory()));
            List<Item> items = itemRepository.findByPriceBetweenAndCategory(params.getMinPrice(), params.getMaxPrice(), category);
            return itemDto.mapToItemResponse(items);
        }
        else {
            List<Item> items = itemRepository.findByPriceBetween(params.getMinPrice(), params.getMaxPrice());
            return itemDto.mapToItemResponse(items);
        }

    }

    public ItemResponse sortDsc() {
        List<Item> items = itemRepository.findAllByOrderByPriceDesc();
        return itemDto.mapToItemResponse(items);
    }

    public ItemResponse sortAsc() {
        List<Item> items = itemRepository.findAllByOrderByPriceAsc();
        return itemDto.mapToItemResponse(items);
    }

    public ItemResponse sortAlph() {
        List<Item> items = itemRepository.findAllByOrderByTitleAsc();
        return itemDto.mapToItemResponse(items);
    }
}
