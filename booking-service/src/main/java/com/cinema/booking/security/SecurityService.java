package com.cinema.booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    @Bean
    public UserDetailsService userDetailsService () {
        return username -> {
            throw new UsernameNotFoundException("No user Lookup");
        };
    }
}
