package com.cinema.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient authClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Bean
    public WebClient catalogClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }
}
