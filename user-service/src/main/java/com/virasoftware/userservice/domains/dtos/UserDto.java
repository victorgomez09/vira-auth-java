package com.virasoftware.userservice.domains.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String phone;
}
