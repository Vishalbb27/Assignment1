package com.springSecurity.security.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springSecurity.security.dto.UserDto;
import com.springSecurity.security.entity.User;
import com.springSecurity.security.mapper.TaskMapper;
import com.springSecurity.security.mapper.UserMapper;
import com.springSecurity.security.repository.UserRepository;
import com.springSecurity.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	public UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users= userRepository.findAll();
		List<UserDto> userDto= users.stream().map(user -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
		return userDto;
	}

}
