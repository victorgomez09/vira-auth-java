package com.virasoftware.authservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private Long id;
    @NotBlank(message = "Please, enter your username!")
    private String username;
    @NotBlank(message = "Please, enter your password!")
    private String password;
    @Email(message = "Invalid email!")
    @NotBlank(message = "Please, enter your email!")
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}
