package com.virasoftware.authservice.services;

import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virasoftware.authservice.domains.dtos.EmailDto;
import com.virasoftware.authservice.domains.dtos.EmailUpdateDto;
import com.virasoftware.authservice.domains.dtos.UserDto;
import com.virasoftware.authservice.domains.entities.AuthUser;
import com.virasoftware.authservice.feign.UserFeignClient;
import com.virasoftware.authservice.repository.UserRepository;
import com.virasoftware.common.dto.EmailVerificationDto;
import com.virasoftware.common.exception.ConflictException;
import com.virasoftware.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserFeignClient userFeignClient;
    private final UserRepository userRepository;
    private final KafkaTemplate<String, EmailVerificationDto> emailVerificationKafkaTemplate;
    private final KafkaTemplate<String, EmailUpdateDto> emailUpdateKafkaTemplate;

    public void sendActivationCode(String username, String email, String activationCode) {
        EmailVerificationDto emailVerificationDto = new EmailVerificationDto(username, email, activationCode);
        emailVerificationKafkaTemplate.send("user-email-verify-topic", emailVerificationDto);
    }

    @Transactional
    public void updateEmail(Long userId, EmailDto requestDto) {
        ResponseEntity<UserDto> response = userFeignClient.findByEmail(requestDto.getEmail());
        if (response.getStatusCode() == HttpStatusCode.valueOf(200)) {
            throw new ConflictException("User with email already exists!");
        }

        response = userFeignClient.findById(userId);
        if (response.getStatusCode() == HttpStatusCode.valueOf(200)) {
            throw new NotFoundException("User not exists!");
        }
        UserDto user = response.getBody();
        user.setEmail(requestDto.getEmail());

        AuthUser authUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not exists!"));
        String activationCode = UUID.randomUUID().toString();
        authUser.setActivationCode(activationCode);
        userRepository.save(authUser);

        sendActivationCode(user.getUsername(), user.getEmail(), activationCode);

        EmailUpdateDto emailUpdateDto = new EmailUpdateDto(userId, requestDto.getEmail());
        emailUpdateKafkaTemplate.send("user-email-update-topic", emailUpdateDto);
    }

    public void verifyEmail(String activationCode) {
        AuthUser user = userRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new NotFoundException("Activation code not found or already used!"));
        user.setActivationCode(null);
        userRepository.save(user);
    }

}
