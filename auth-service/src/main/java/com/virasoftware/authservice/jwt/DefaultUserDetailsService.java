package com.virasoftware.authservice.jwt;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.virasoftware.authservice.dtos.UserDto;
import com.virasoftware.authservice.feign.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {
    // TODO: check call user microservice
    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<UserDto> response = userFeignClient.findByUsername(username);
        if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserDto user = response.getBody();

        return new User(username, user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
    }

}