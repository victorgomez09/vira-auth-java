//package com.virasoftware.userservice.configs;
//
//import java.util.HashMap;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//
//import com.virasoftware.userservice.domains.dtos.EmailUpdateDto;
//import com.virasoftware.userservice.domains.dtos.UserDto;
//import com.virasoftware.userservice.kafka.EmailUpdateDeserializer;
//import com.virasoftware.userservice.kafka.UserDtoDeserializer;
//
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Bean
//    ConsumerFactory<String, UserDto> userConsumerFactory() {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserDtoDeserializer.class);
//        
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UserDto>> userKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, UserDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(userConsumerFactory());
//        
//        return factory;
//    }
//
//    @Bean
//    ConsumerFactory<String, EmailUpdateDto> emailUpdateConsumerFactory() {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EmailUpdateDeserializer.class);
//        
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EmailUpdateDto>> emailKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, EmailUpdateDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(emailUpdateConsumerFactory());
//        
//        return factory;
//    }
//
//}
