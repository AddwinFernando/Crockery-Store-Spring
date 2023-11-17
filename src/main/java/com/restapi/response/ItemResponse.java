package com.restapi.response;

import com.restapi.model.Item;
import com.restapi.request.ItemRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemResponse {
    private List<ItemRequest> Items = new ArrayList<>();
}
