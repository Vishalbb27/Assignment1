package com.springSecurity.security.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springSecurity.security.entity.User;
import com.springSecurity.security.repository.UserRepository;

@Service //Creates a bean for this method
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	
	
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()->new UsernameNotFoundException("User does not exists"));
		
		Set<GrantedAuthority>  authorities = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		
		
		return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(), authorities);
	}

}
