package com.virasoftware.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.virasoftware.authservice.domains.dtos.RegisterDto;
import com.virasoftware.authservice.domains.dtos.UserDto;

@FeignClient(name = "user-service", path = "/api/v1/user")
public interface UserFeignClient {

    @PostMapping("/create")
    ResponseEntity<UserDto> create(RegisterDto requestDto);

    @GetMapping("/{userId}")
    ResponseEntity<UserDto> findById(Long id);

    @GetMapping("/username/{username}")
    ResponseEntity<UserDto> findByUsername(@PathVariable String username);

    @GetMapping("/email/{email}")
    ResponseEntity<UserDto> findByEmail(String email);
}
