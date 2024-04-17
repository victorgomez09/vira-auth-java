package com.virasoftware.docservice.domains.dtos;

import java.util.Set;

import com.virasoftware.docservice.enums.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	//TODO: validate what will if user post with id
    private Long id; 
    private String username;
    private String email;
    private Set<Permission> permissions;
    private String firstName;
    private String lastName;
    private String phone;
    
}
