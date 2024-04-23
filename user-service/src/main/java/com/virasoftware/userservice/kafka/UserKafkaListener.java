//package com.virasoftware.userservice.kafka;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import com.virasoftware.userservice.domains.dtos.UserDto;
//import com.virasoftware.userservice.services.UserService;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserKafkaListener {
//    private final UserService userService;
//
//    @KafkaListener(topics = {"user-registration-topic"}, groupId = "group-id", containerFactory = "userKafkaListenerContainerFactory")
//    public void listenUserDto(UserDto userDto) {
//        System.out.println("New message: " + userDto.toString());
//        userService.createNewUser(userDto);
//    }
//
//}
