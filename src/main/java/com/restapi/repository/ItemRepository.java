package com.restapi.repository;

import com.restapi.model.Category;
import com.restapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Item> findByPriceBetweenAndCategory(Double minPrice, Double maxPrice, Category category);

    List<Item> findAllByOrderByPriceDesc();

    List<Item> findAllByOrderByPriceAsc();

    List <Item> findAllByOrderByTitleAsc();
    List <Item> findAllByOrderByTitleDesc();
}
