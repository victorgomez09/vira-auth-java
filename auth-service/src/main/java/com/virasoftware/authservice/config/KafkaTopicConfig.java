//package com.virasoftware.authservice.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    @Bean
//    NewTopic userRegistrationTopic() {
//        return TopicBuilder.name("user-registration-topic").build();
//    }
//
//    @Bean
//    NewTopic emailVerificationTopic() {
//        return TopicBuilder.name("user-email-verify-topic").build();
//    }
//
//    @Bean
//    NewTopic emailUpdateTopic() {
//        return TopicBuilder.name("user-email-update-topic").build();
//    }
//
//}
