package com.virasoftware.authservice.services;

import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virasoftware.authservice.domains.entities.AuthUser;
import com.virasoftware.authservice.dtos.LoginRequestDto;
import com.virasoftware.authservice.dtos.RefreshResponseDto;
import com.virasoftware.authservice.dtos.RegisterDto;
import com.virasoftware.authservice.dtos.UserDto;
import com.virasoftware.authservice.feign.UserFeignClient;
import com.virasoftware.authservice.repository.UserRepository;
import com.virasoftware.common.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final UserFeignClient userFeignClient;
	private final AuthenticationManager authenticationManager;
	private final RefreshTokenService refreshTokenService;
	private final EmailService emailService;

	@Transactional
	public UserDto register(RegisterDto registerDto) {
		try {
			UserDto userDto = new UserDto();
			userDto.setFirstName(registerDto.getUsername());
			userDto.setLastName(registerDto.getLastName());
			userDto.setPhone(registerDto.getPhone());

			ResponseEntity<UserDto> response = userFeignClient.create(registerDto);
			if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
				throw new Exception("error");
			}
			UserDto userCreated = response.getBody();

			if (userRepository.existsById(userCreated.getId())) {
				throw new ConflictException("User with username or email already exists!");
			}

			AuthUser user = new AuthUser();
			String activationCode = UUID.randomUUID().toString();
			user.setUserId(userCreated.getId());
			user.setActivationCode(activationCode);
			userRepository.save(user);

			emailService.sendActivationCode(userDto.getUsername(), userDto.getEmail(), activationCode);

			return userCreated;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public RefreshResponseDto login(LoginRequestDto loginRequestDto) {
		try {
			ResponseEntity<UserDto> response = userFeignClient.findByUsername(loginRequestDto.getUsername());
			if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
				throw new UsernameNotFoundException("error");
			}
			AuthUser user = userRepository.findByUserId(response.getBody().getId()).orElseThrow();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(response.getBody().getUsername(),
					loginRequestDto.getPassword()));

			return refreshTokenService.createRefreshToken(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
