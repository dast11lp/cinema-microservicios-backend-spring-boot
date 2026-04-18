package com.cinema.catalog.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class SecurityConfig {
    @Autowired
    private JWTFIlter jwtfIlter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic( basic -> basic.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .exceptionHandling( ex -> ex.authenticationEntryPoint(
                        (request,response,e) -> response
                                .sendError(HttpServletResponse.SC_UNAUTHORIZED)
                ))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtfIlter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
