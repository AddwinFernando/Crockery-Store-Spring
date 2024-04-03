package com.restapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.AppUser;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {

    private Long id;

    private String address;

    private String city;

    private Integer zipcode;

    private Long userId;
}
