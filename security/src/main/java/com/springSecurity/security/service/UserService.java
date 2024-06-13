package com.springSecurity.security.service;

import java.util.List;

import com.springSecurity.security.dto.UserDto;

public interface UserService {
	
	public List<UserDto> getAllUsers();

}
