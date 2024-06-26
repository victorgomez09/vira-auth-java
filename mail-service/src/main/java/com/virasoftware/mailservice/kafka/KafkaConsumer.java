package com.virasoftware.mailservice.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.virasoftware.mailservice.dto.EmailVerificationDto;

@Configuration
public class KafkaConsumer {

    @Bean
    ConsumerFactory<String, EmailVerificationDto> consumerFactory() {	
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EmailVerificationDtoDeserializer.class);
        
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EmailVerificationDto>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailVerificationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        
        return factory;
    }

}
