package com.virasoftware.authservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virasoftware.authservice.dtos.LoginRequestDto;
import com.virasoftware.authservice.dtos.RefreshRequestDto;
import com.virasoftware.authservice.dtos.RefreshResponseDto;
import com.virasoftware.authservice.dtos.RegisterDto;
import com.virasoftware.authservice.dtos.UserDto;
import com.virasoftware.authservice.services.AuthService;
import com.virasoftware.authservice.services.RefreshTokenService;
import com.virasoftware.common.aspects.ValidateErrors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization API", description = "Register, login and refresh authorization")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Operation(summary = "Register user", description = "Register user")
    @PostMapping("/register")
    @ValidateErrors
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterDto registerDto, BindingResult result) {
        UserDto userDto = authService.register(registerDto);
        
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Login user", description = "Login user")
    @PostMapping("/login")
    @ValidateErrors
    public ResponseEntity<RefreshResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto, BindingResult result) {
        RefreshResponseDto responseDto = authService.login(loginRequestDto);
        
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Refresh tokens", description = "Refresh tokens")
    @PostMapping("/refresh")
    @ValidateErrors
    public ResponseEntity<RefreshResponseDto> refresh(@RequestBody @Valid RefreshRequestDto refreshRequestDto, BindingResult result) {
        RefreshResponseDto responseDto = refreshTokenService.refreshToken(refreshRequestDto);
        
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
