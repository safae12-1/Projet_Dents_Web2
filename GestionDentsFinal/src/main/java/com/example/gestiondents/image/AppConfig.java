package com.example.gestiondents.image;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Base64Utils base64Utils() {
        return new Base64Utils();
    }
}
