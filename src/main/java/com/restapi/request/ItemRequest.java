package com.restapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private Long id;

    private String title;

    private String description;

    private Double price;

    private byte[] photo;

    private Long category;

    public ItemRequest(String title, String description, Double price, byte[] photo, Long category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
    }
}
