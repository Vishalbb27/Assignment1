package com.springSecurity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springSecurity.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	
	
}
