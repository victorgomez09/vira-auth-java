package com.virasoftware.mailservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.virasoftware.mailservice.dto.EmailVerificationDto;
import com.virasoftware.mailservice.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailListener {
    private final EmailService emailService;

    @KafkaListener(topics = "user-email-verify-topic", groupId = "group-id")
    public void verifyEmail(EmailVerificationDto emailVerificationDto) {
        System.out.println("Verify email: " + emailVerificationDto.getEmail());
        emailService.sendVerification(emailVerificationDto);
    }

}
