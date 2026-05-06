package com.cinema.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secrectKey;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String JwtGenerator(String email) {
		return JWT.create().withSubject("User Details")
				.withClaim("email", email)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + expiration ))
				.withIssuer("cinePop")
				.sign(Algorithm.HMAC256(secrectKey));
	}

	public String JWtValidator(String token) {
		
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secrectKey))
				   .withSubject("User Details")
				   .withIssuer("cinePop")
				   .build();
		
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("email").asString();
	}
}




