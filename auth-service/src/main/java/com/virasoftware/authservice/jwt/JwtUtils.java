package com.virasoftware.authservice.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.virasoftware.authservice.domains.entities.AuthUser;
import com.virasoftware.authservice.dtos.AccessToken;
import com.virasoftware.authservice.dtos.UserDto;
import com.virasoftware.authservice.feign.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Duration jwtLifeTime;

    private final UserFeignClient userFeignClient;

    public AccessToken createAccessToken(AuthUser authUser) {
        try {
            ResponseEntity<UserDto> response = userFeignClient.findById(authUser.getUserId());
            if (response.getStatusCode() != HttpStatusCode.valueOf(200) || response.getBody() == null) {
                throw new UsernameNotFoundException("User not found");
            }
            UserDto user = response.getBody();

            Instant expiresAt = ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(jwtLifeTime.toMinutes())
                    .toInstant();
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getId())
                    .withClaim("roles", user.getRoles())
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(secret));

            return new AccessToken(authUser.getUserId(), token, expiresAt, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}