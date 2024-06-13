package com.springSecurity.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springSecurity.security.entity.Task;
import com.springSecurity.security.entity.User;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByAssignedTo(User user);
}
