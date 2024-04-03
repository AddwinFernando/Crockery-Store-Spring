package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest {
    private int orderId;
    private int statusId;
}
