package com.springSecurity.security.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springSecurity.security.dto.JtAuthResponse;
import com.springSecurity.security.dto.LoginDto;
import com.springSecurity.security.dto.RegisterDto;
import com.springSecurity.security.dto.UserDto;
import com.springSecurity.security.entity.Role;
import com.springSecurity.security.entity.User;
import com.springSecurity.security.exception.APIException;
import com.springSecurity.security.mapper.UserMapper;
import com.springSecurity.security.repository.RoleRepository;
import com.springSecurity.security.repository.UserRepository;
import com.springSecurity.security.security.JwtTokenProvider;
import com.springSecurity.security.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository; 
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,JwtTokenProvider jwtTokenProvider) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String register(RegisterDto registerDto) {
		// Check username is already exist or not
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");

		}

		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Set<Role> roles = new HashSet();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);

		user.setRoles(roles);

		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public JtAuthResponse login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));// Implements
																												// authentication
		JtAuthResponse jwtAuthResponse = new JtAuthResponse();																										// method
		if(!authentication.isAuthenticated()) {
			throw new APIException(HttpStatus.NOT_FOUND, "Username does not exist please register.");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token =jwtTokenProvider.generateToken(authentication);
		
		jwtAuthResponse.setAccessToken(token);
		
		String role = null;
		Optional<User> userOptional =userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
		if(userOptional.isPresent()) {
			User user= userOptional.get();
			Optional<Role> optionalRole = user.getRoles().stream().findFirst();
			if(optionalRole.isPresent()) {
				Role userRole = optionalRole.get();
				role = userRole.getName();
			}
		}
		
		jwtAuthResponse.setRole(role);
		return jwtAuthResponse;
	}

	@Override
	public String logout() {
		// TODO Auto-generated method stub
		return "Logout Successfull";
	}

	@Override
	public UserDto getUserDetails(String username) {
		User user = userRepository.findByUsername(username).get();
		UserDto userDto = UserMapper.mapToUserDto(user);
		return userDto;
	}

}
