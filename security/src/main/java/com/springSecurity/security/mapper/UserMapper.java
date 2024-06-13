package com.springSecurity.security.mapper;

import com.springSecurity.security.dto.TaskDto;
import com.springSecurity.security.dto.UserDto;
import com.springSecurity.security.entity.Task;
import com.springSecurity.security.entity.User;

public class UserMapper {
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(), user.getName(), user.getUsername(),user.getEmail());
		return userDto;
	}
}
