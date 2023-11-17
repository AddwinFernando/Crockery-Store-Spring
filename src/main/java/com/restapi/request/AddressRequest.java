package com.restapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class AddressRequest {

    private Long id;

    private String address;

    private String city;

    private Integer zipcode;

    private Long userId;
}
