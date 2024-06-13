package com.springSecurity.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.security.dto.JtAuthResponse;
import com.springSecurity.security.dto.LoginDto;
import com.springSecurity.security.dto.RegisterDto;
import com.springSecurity.security.dto.UserDto;
import com.springSecurity.security.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;


	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/register") 
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String response = authService.register(registerDto);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JtAuthResponse> login(@RequestBody LoginDto loginDto){
		JtAuthResponse jwtAuthResponse = authService.login(loginDto);
		
		
		
		return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<?> userDetails(@PathVariable("username") String username){
		UserDto userDto = authService.getUserDetails(username);
		
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
}
