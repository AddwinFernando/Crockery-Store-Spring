package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequest {
    private Long id;
    private int stock;
}
