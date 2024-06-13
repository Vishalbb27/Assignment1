package com.springSecurity.security.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springSecurity.security.dto.TaskDto;
import com.springSecurity.security.entity.Task;
import com.springSecurity.security.entity.User;
import com.springSecurity.security.exception.APIException;
import com.springSecurity.security.exception.UsernameNotFoundException;
import com.springSecurity.security.mapper.TaskMapper;
import com.springSecurity.security.repository.TaskRepository;
import com.springSecurity.security.repository.UserRepository;
import com.springSecurity.security.service.AuthService;
import com.springSecurity.security.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;
	private UserRepository userRepository;


	public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	@Override
	public String addTask(TaskDto taskDto) {
		User user = userRepository.findById(Long.parseLong(taskDto.getAssignedTo())).get();
		Task task = TaskMapper.mapToTask(taskDto, user);
		Task savedTask = taskRepository.save(task);
		
		return savedTask.getTaskName();
	}

	@Override
	public List<TaskDto> getTask() {
		List<Task> tasks = taskRepository.findAll();
		List<TaskDto> taskDto = mapFromTaskToTaskDto(tasks);
		
		return taskDto;
	}

	private List<TaskDto> mapFromTaskToTaskDto(List<Task> tasks) {
		return tasks.stream().map(task -> TaskMapper.mapToTaskDto(task)).collect(Collectors.toList());
	}

	@Override
	public TaskDto getTaskById(int id) {
		
		Task task = taskRepository.findById(id).stream().findFirst().orElse(null);
		if(task != null){
            return  TaskMapper.mapToTaskDto(task);
        }else{
            throw new UsernameNotFoundException("User Not found!!");
        }
	}

	@Override
	public TaskDto updateTaskById(int id, TaskDto taskDto) {
		getTaskById(id);
		User user = userRepository.findById(Long.parseLong(taskDto.getAssignedTo())).get();
		Task task = TaskMapper.mapToTask(taskDto, user);
		task.setId(id);
		Task savedTask = taskRepository.save(task);
		return TaskMapper.mapToTaskDto(savedTask);
	}

	@Override
	public String deleteTaskById(int id) {
		// TODO Auto-generated method stub
		getTaskById(id);
		taskRepository.deleteById(id);
		return "deleted";
	}

	@Override
	public List<TaskDto> getByUser(String id) {
		User user = userRepository.findById(Long.parseLong(id)).get();
		List<Task> tasks = taskRepository.findByAssignedTo(user);
		List<TaskDto> taskDtos = mapFromTaskToTaskDto(tasks);
		return taskDtos;
	}

	@Override
	public TaskDto updateTaskByisDone(int id, Boolean isDone) {
		Task task = taskRepository.findById(id).stream().findFirst().orElse(null);
		if(task != null){
            task.setIsDone(isDone);
            taskRepository.save(task);
            TaskDto taskDto = TaskMapper.mapToTaskDto(task);
            return taskDto;
        }else{
            throw new UsernameNotFoundException("Task Not found!!");
        }
	}

}
