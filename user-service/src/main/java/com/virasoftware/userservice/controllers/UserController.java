package com.virasoftware.userservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virasoftware.common.aspects.ValidateErrors;
import com.virasoftware.common.dto.ResponseMessage;
import com.virasoftware.userservice.domains.dtos.UserDto;
import com.virasoftware.userservice.mappers.UserMapper;
import com.virasoftware.userservice.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Interaction with users")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get User by ID", description = "Get User by ID")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto responseDto = userMapper.toDto(userService.getUserById(userId));

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Get User by username", description = "Get User by username")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto responseDto = userMapper.toDto(userService.getUserByUsername(username));

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Update User by ID", description = "Update User by ID")
    @PutMapping("/{userId}")
    @ValidateErrors
    public ResponseEntity<UserDto> updateUserById(
            @PathVariable Long userId,
            @RequestBody @Valid UserDto requestDto,
            BindingResult result) {
        UserDto responseDto = userMapper.toDto(userService.updateUserById(userId, userMapper.toEntity(requestDto)));

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Get Current User", description = "Get Current User by Authentication")
    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser(@Parameter(hidden = true) @RequestHeader("X-User-Id") Long userId) {
        UserDto responseDto = userMapper.toDto(userService.getUserById(userId));

        return ResponseEntity.ok(responseDto);
    }
    
    @Operation(summary = "Create new User", description = "Create new User by Authentication")
    @PostMapping("/create")
    @ValidateErrors
    public ResponseEntity<UserDto> createCurrentUser(
            @RequestBody @Valid UserDto requestDto,
            BindingResult result) {
        UserDto responseDto = userMapper.toDto(userService.createUser(userMapper.toEntity(requestDto)));

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Update Current User", description = "Update Current User by Authentication")
    @PutMapping
    @ValidateErrors
    public ResponseEntity<UserDto> updateCurrentUser(
            @Parameter(hidden = true) @RequestHeader("X-User-Id") Long userId,
            @RequestBody @Valid UserDto requestDto,
            BindingResult result) {
        UserDto responseDto = userMapper.toDto(userService.updateUserById(userId, userMapper.toEntity(requestDto)));

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Partial Update Current User", description = "Partial Update Current User by Authentication")
    @PatchMapping
    public ResponseEntity<UserDto> patchCurrentUser(
            @Parameter(hidden = true) @RequestHeader("X-User-Id") Long userId,
            @RequestBody UserDto requestDto) {
        UserDto responseDtp = userMapper.toDto(userService.patchUserById(userId, userMapper.toEntity(requestDto)));

        return ResponseEntity.ok(responseDtp);
    }

    @Operation(summary = "Delete Current User", description = "Delete Current User by Authentication")
    @DeleteMapping // TODO: delete mapping by id only by admin. need to validate is this user is
                   // current? i think probably
    public ResponseEntity<ResponseMessage> deleteCurrentUser(
            @Parameter(hidden = true) @RequestHeader("X-User-Id") Long userId) {
        userService.deleteUserById(userId);

        return ResponseEntity.noContent().build();
    }

}