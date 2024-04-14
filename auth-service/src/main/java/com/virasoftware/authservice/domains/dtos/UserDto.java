package com.virasoftware.authservice.domains.dtos;

import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.virasoftware.authservice.domains.entities.User;
import com.virasoftware.common.dto.Dto;
import com.virasoftware.common.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Dto<User> {
	//TODO: validate what will if user post with id
    private Long id; 
    private String username;
    private String email;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String phone;

    @Override
    public UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public User toEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
