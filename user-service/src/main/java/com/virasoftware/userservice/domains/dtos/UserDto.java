package com.virasoftware.userservice.domains.dtos;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.virasoftware.common.dto.Dto;
import com.virasoftware.userservice.domains.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Dto<User> {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
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
