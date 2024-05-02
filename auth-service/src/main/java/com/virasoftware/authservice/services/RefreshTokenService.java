package com.virasoftware.authservice.services;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.virasoftware.authservice.domains.entities.RefreshToken;
import com.virasoftware.authservice.dtos.RefreshRequestDto;
import com.virasoftware.authservice.dtos.RefreshResponseDto;
import com.virasoftware.authservice.domains.entities.AuthUser;
import com.virasoftware.authservice.jwt.JwtUtils;
import com.virasoftware.authservice.repository.RefreshTokenRepository;
import com.virasoftware.common.exception.NotFoundException;
import com.virasoftware.common.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtils jwtUtils;

    @Value("${jwt.refreshToken.expiration}")
    private Duration refreshTokenLifeTime;

    public RefreshResponseDto createRefreshToken(AuthUser user) {
        if (user.getRefreshToken() != null) {
            refreshTokenRepository.delete(user.getRefreshToken());
            user.setRefreshToken(null);
        }
        var accessToken = jwtUtils.createAccessToken(user);

        var refreshToken = refreshTokenRepository.save(RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiration(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(refreshTokenLifeTime.toMinutes())
                        .toInstant())
                .build());

        return RefreshResponseDto.builder()
        		.id(user.getUserId())
                .accessToken(accessToken.getAccessToken())
                .accessTokenExpiration(accessToken.getAccessTokenExpiration())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiration(refreshToken.getExpiration())
                .build();
    }

    public RefreshResponseDto refreshToken(RefreshRequestDto refreshRequestDto) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshRequestDto.getRefreshToken())
                .orElseThrow(
                        () -> new NotFoundException("Refresh token not found: " + refreshRequestDto.getRefreshToken()));
        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new UnauthorizedException("Refresh token is expired: " + refreshRequestDto.getRefreshToken());
        }
        var accessToken = jwtUtils.createAccessToken(refreshToken.getUser());
        updateToken(refreshToken);

        return RefreshResponseDto.builder()
                .accessToken(accessToken.getAccessToken())
                .accessTokenExpiration(accessToken.getAccessTokenExpiration())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiration(refreshToken.getExpiration())
                .build();
    }

    private void updateToken(RefreshToken refreshToken) {
        refreshToken.setExpiration(
                ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(refreshTokenLifeTime.toMinutes()).toInstant());
        refreshTokenRepository.save(refreshToken);
    }

}
