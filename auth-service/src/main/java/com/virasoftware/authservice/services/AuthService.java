package com.virasoftware.authservice.services;

import java.util.Set;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virasoftware.authservice.domains.dtos.LoginRequestDto;
import com.virasoftware.authservice.domains.dtos.RefreshResponseDto;
import com.virasoftware.authservice.domains.dtos.RegisterDto;
import com.virasoftware.authservice.domains.dtos.UserDto;
import com.virasoftware.authservice.domains.entities.User;
import com.virasoftware.authservice.repository.UserRepository;
import com.virasoftware.common.enums.Role;
import com.virasoftware.common.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final RefreshTokenService refreshTokenService;
	private final KafkaTemplate<String, UserDto> userDtoKafkaTemplate;
	private final EmailService emailService;

	@Transactional
	public UserDto register(RegisterDto registerDto) {
		if (userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail())) {
			throw new ConflictException("User with username or email already exists!");
		}

		User user = new User();
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setEmail(registerDto.getEmail());
		user.setRoles(Set.of(Role.USER));
		String activationCode = UUID.randomUUID().toString();
		user.setActivationCode(activationCode);

		User savedUser = userRepository.save(user);

		UserDto userDto = new UserDto().fromEntity(savedUser);
		userDto.setFirstName(registerDto.getUsername());
		userDto.setLastName(registerDto.getLastName());
		userDto.setPhone(registerDto.getPhone());
		userDtoKafkaTemplate.send("user-registration-topic", userDto);

		emailService.sendActivationCode(userDto.getUsername(), userDto.getEmail(), activationCode);

		return userDto;
	}

	public RefreshResponseDto login(LoginRequestDto loginRequestDto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
		User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow();

		return refreshTokenService.createRefreshToken(user);
	}

}
