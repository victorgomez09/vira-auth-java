package com.virasoftware.authservice.services;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virasoftware.authservice.domains.dtos.EmailDto;
import com.virasoftware.authservice.domains.dtos.EmailUpdateDto;
import com.virasoftware.authservice.domains.entities.User;
import com.virasoftware.authservice.repository.UserRepository;
import com.virasoftware.common.dto.EmailVerificationDto;
import com.virasoftware.common.exception.ConflictException;
import com.virasoftware.common.exception.NotFoundException;
import com.virasoftware.common.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
    private final UserRepository userRepository;
    private final KafkaTemplate<String, EmailVerificationDto> emailVerificationKafkaTemplate;
    private final KafkaTemplate<String, EmailUpdateDto> emailUpdateKafkaTemplate;

    public void sendActivationCode(String username, String email, String activationCode) {
        EmailVerificationDto emailVerificationDto = new EmailVerificationDto(username, email, activationCode);
        emailVerificationKafkaTemplate.send("user-email-verify-topic", emailVerificationDto);
    }

    @Transactional
    public void updateEmail(Long userId, EmailDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new ConflictException("User with email already exists!");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UnauthorizedException("User not found with UserID: " + userId));
        user.setEmail(requestDto.getEmail());
        String activationCode = UUID.randomUUID().toString();
        user.setActivationCode(activationCode);
        userRepository.save(user);

        sendActivationCode(user.getUsername(), user.getEmail(), activationCode);

        EmailUpdateDto emailUpdateDto = new EmailUpdateDto(userId, requestDto.getEmail());
        emailUpdateKafkaTemplate.send("user-email-update-topic", emailUpdateDto);
    }

    public void verifyEmail(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode).orElseThrow(() -> new NotFoundException("Activation code not found or already used!"));
        user.setActivationCode(null);
        userRepository.save(user);
    }

}
