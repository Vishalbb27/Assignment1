package com.springSecurity.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springSecurity.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	Boolean existsByEmail(String email);//Standard method existsBy
	
	Optional<User> findByUsernameOrEmail(String username,String email);
	
	Boolean existsByUsername(String username);
	
}
