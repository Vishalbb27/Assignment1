package com.springSecurity.security.service;

import com.springSecurity.security.dto.JtAuthResponse;
import com.springSecurity.security.dto.LoginDto;
import com.springSecurity.security.dto.RegisterDto;
import com.springSecurity.security.dto.UserDto;
import com.springSecurity.security.entity.User;

public interface AuthService {
	
	String register(RegisterDto registerDto);
	
	JtAuthResponse login(LoginDto loginDto);
	
	UserDto getUserDetails(String username);
	
	String logout();
}
