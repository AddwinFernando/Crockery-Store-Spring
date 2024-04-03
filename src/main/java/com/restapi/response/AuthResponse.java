package com.restapi.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private Long id;
    private String username;
    private String name;
    private String role;
}
