package com.virasoftware.authservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
@AllArgsConstructor
@Getter
public class AccessToken {
	private String id;
	private String accessToken;
    private Instant accessTokenExpiration;
    private String refreshToken;
    private Instant refreshTokenExpiration;
}
