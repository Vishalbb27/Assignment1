package com.springSecurity.security.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;

	// Generate JWT token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		System.out.println("Hello  "+ username);
		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,jwtSecret)
				.compact();// Club all the properties and convert jwt into URL-safe string representation
		
		return token;
	}

	private Key key() {
		System.out.println(jwtSecret);
		System.out.println(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)));
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	 
	//Get username from jwt token
	public String getUsername(String token) {
		Claims claims= Jwts.parser()
		.setSigningKey(key())
		.build()
		.parseClaimsJws(token)
		.getBody();
		
		
		String username = claims.getSubject();
		return username;
	}
	
	
	//Validate JWT Token
	public boolean validateToken(String token) { 
		System.out.println(token);
		Jwts.parser()
		.setSigningKey(key())
		.build() 
		.parseClaimsJws(token);
		return true;
	}
}
