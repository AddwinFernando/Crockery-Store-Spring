package com.restapi.response;

import com.restapi.model.Address;
import com.restapi.model.OrderedItem;
import com.restapi.request.OrderRequest;
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
public class OrderResponse {
    private Long id;
    private List<OrderedItem> orderedItems;
    private Long userId;
    private String userName;
    private String name;
    private Address address;
    private String orderStatus;
}
