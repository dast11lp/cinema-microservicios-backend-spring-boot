package com.cinema.booking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);

            try {
                String email = jwtUtil.validate(jwt);
                var context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(new UsernamePasswordAuthenticationToken(email, null, List.of()));
                SecurityContextHolder.setContext(context);

            }catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
            }
        }
        filterChain.doFilter(request, response);
    }
}


