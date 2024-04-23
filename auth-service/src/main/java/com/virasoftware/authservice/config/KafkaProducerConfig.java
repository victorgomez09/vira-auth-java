//package com.virasoftware.authservice.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import com.virasoftware.authservice.domains.dtos.EmailUpdateDto;
//import com.virasoftware.authservice.domains.dtos.UserDto;
//import com.virasoftware.authservice.kafka.EmailUpdateDtoSerializer;
//import com.virasoftware.authservice.kafka.EmailVerificationDtoSerializer;
//import com.virasoftware.authservice.kafka.UserDtoSerializer;
//import com.virasoftware.common.dto.EmailVerificationDto;
//
//@Configuration
//public class KafkaProducerConfig {
//	
//	private final String BOOTSTRAP_SERVERS_CONFIG_URL = "vira-kafka:9092";
//
//    @Bean
//    ProducerFactory<String, UserDto> userProducerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG_URL);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserDtoSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    KafkaTemplate<String, UserDto> userKafkaTemplate() {
//        return new KafkaTemplate<>(userProducerFactory());
//    }
//
//    @Bean
//    ProducerFactory<String, EmailVerificationDto> emailVerificationProducerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG_URL);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmailVerificationDtoSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    KafkaTemplate<String, EmailVerificationDto> emailVerificationKafkaTemplate() {
//        return new KafkaTemplate<>(emailVerificationProducerFactory());
//    }
//
//    @Bean
//    ProducerFactory<String, EmailUpdateDto> emailUpdateProducerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG_URL);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmailUpdateDtoSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    KafkaTemplate<String, EmailUpdateDto> emailUpdateKafkaTemplate() {
//        return new KafkaTemplate<>(emailUpdateProducerFactory());
//    }
//}
