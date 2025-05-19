package com.challenge.transactionalapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
	
   @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordService();
    }

}
