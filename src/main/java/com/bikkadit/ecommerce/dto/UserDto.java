package com.bikkadit.ecommerce.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String UserId;

    private String name;

    private String email;
    private String password;
    private String gender;
    private String about;
}
