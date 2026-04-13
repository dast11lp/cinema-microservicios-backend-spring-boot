package com.cinema.auth.security;



import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity(securedEnabled = true ,prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired private JWTFilter filter;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http
				.cors( cors -> cors.disable())
				.csrf(csrf-> csrf.disable())
				.httpBasic(basic-> basic.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/payments/**", "/users/**")
								.permitAll().anyRequest().authenticated()
				)
				.exceptionHandling(ex -> ex.authenticationEntryPoint(
						(request, response, authException) ->
								response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
				))
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
}
