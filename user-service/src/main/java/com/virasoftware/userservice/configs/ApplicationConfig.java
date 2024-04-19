package com.virasoftware.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.virasoftware.common.aspects.ValidationAspect;

@Configuration
public class ApplicationConfig {

    @Bean
    ValidationAspect validationAspect() {
        return new ValidationAspect();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
