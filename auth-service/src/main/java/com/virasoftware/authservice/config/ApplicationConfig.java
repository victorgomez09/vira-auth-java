package com.virasoftware.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.virasoftware.common.aspects.ValidationAspect;

@Configuration
public class ApplicationConfig {

    @Bean
    ValidationAspect validationAspect() {
        return new ValidationAspect();
    }
}
