package com.virasoftware.authservice.dtos;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
public class RefreshResponseDto {
	private String id;
    private String accessToken;
    private Instant accessTokenExpiration;
    private String refreshToken;
    private Instant refreshTokenExpiration;
}
