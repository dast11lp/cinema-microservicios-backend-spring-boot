package com.cinema.booking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Value("${catalog.service.url}")
    private String bookingServiceUrl;


    @Bean
    public WebClient authClient() {
        return WebClient.builder()
                .baseUrl(authServiceUrl)
                .build();
    }

    @Bean
    public WebClient catalogClient() {
        return WebClient.builder()
                .baseUrl(bookingServiceUrl)
                .build();
    }
}
