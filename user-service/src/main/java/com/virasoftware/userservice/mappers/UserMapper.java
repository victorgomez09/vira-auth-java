package com.virasoftware.userservice.mappers;

import org.mapstruct.Mapper;

import com.virasoftware.userservice.domains.dtos.UserDto;
import com.virasoftware.userservice.domains.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toDto(User entity);
	
	User toEntity(UserDto dto);
}
