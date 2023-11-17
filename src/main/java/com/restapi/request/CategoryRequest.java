package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private Long id;
    private String title;

    public CategoryRequest(String title) {
        this.title = title;
    }
}
