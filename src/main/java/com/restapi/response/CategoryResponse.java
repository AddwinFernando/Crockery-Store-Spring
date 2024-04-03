package com.restapi.response;

import com.restapi.request.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private List<CategoryRequest> categories = new ArrayList<>();
}
