package com.restapi.dto;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Item;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.ItemRequest;
import com.restapi.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemDto {
    @Autowired
    private CategoryRepository categoryRepository;

    public ItemResponse mapToItemResponse(List<Item> items) {
        ItemResponse itemResponse = new ItemResponse();
        List<ItemRequest> itemRequests = new ArrayList<>();
        for (Item item : items) {
            itemRequests.add(new ItemRequest(item.getId(), item.getTitle(), item.getDescription(), item.getPrice(), item.getPhoto(), item.getCategory().getId(), item.getStock()));
        }
        itemResponse.setItems(itemRequests);
        return itemResponse;
    }

    public Item mapToItem(ItemRequest itemRequest) {
        Item item = new Item();
        if (itemRequest.getId() != null) {
            item.setId(itemRequest.getId());
        }
        item.setTitle(itemRequest.getTitle());
        item.setDescription(itemRequest.getDescription());
        item.setPrice(itemRequest.getPrice());
        item.setPhoto(itemRequest.getPhoto());
        item.setStock(itemRequest.getStock());
        Category category = categoryRepository.findById(itemRequest.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Item", "ItemId", item.getId()));
        item.setCategory(category);
        return item;
    }
}
