package com.virasoftware.userservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.virasoftware.userservice.domains.dtos.EmailUpdateDto;
import com.virasoftware.userservice.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailKafkaListener {
    private final UserService userService;

    @KafkaListener(topics = {"user-email-update-topic"}, groupId = "group-id", containerFactory = "emailKafkaListenerContainerFactory")
    public void listenEmailUpdateDto(EmailUpdateDto emailUpdateDto) {
        System.out.println("New message: " + emailUpdateDto.toString());
        userService.updateEmail(emailUpdateDto);
    }

}