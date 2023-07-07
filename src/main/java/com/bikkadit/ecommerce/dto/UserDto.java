package com.bikkadit.ecommerce.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String UserId;
    @Size(min=3, max=30, message = "Name Must be contains Minimum 3 or Maximum 30 characters.")
    private String name;
    @Email(message = "Please enter a valid email id.")
    private String email;
    @Size(min=8, max=20, message = "Password Must be Minimum 8 or Maximum 20 Character.")
    private String password;
    @Size(min=4, max = 6)
    private String gender;
    @Size(min=1, max=1000, message = "Maximum length may be 1000 characters.")
    private String about;
    private String imageName;
}
