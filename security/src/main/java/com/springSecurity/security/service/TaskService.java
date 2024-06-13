package com.springSecurity.security.service;

import java.util.List;

import com.springSecurity.security.dto.TaskDto;
import com.springSecurity.security.entity.Task;

public interface TaskService {
	public String addTask(TaskDto taskDto);
	
	public List<TaskDto> getTask();
	
	public TaskDto getTaskById(int id);
	
	public TaskDto updateTaskById(int id, TaskDto taskDto);
	
	public String deleteTaskById(int id);
	
	public List<TaskDto> getByUser(String id);
	
	public TaskDto updateTaskByisDone(int id, Boolean isDone);
	
}
