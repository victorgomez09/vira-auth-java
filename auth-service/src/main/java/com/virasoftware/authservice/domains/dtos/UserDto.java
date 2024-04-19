package com.virasoftware.authservice.domains.dtos;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.virasoftware.authservice.domains.entities.AuthUser;
import com.virasoftware.common.dto.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Dto<AuthUser> {
    // TODO: validate what will if user post with id
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String phone;

    @Override
    public UserDto fromEntity(AuthUser user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public AuthUser toEntity() {
        AuthUser user = new AuthUser();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
