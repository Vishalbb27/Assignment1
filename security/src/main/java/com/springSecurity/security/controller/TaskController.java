package com.springSecurity.security.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.security.dto.TaskDto;
import com.springSecurity.security.service.EmployeeService;
import com.springSecurity.security.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping
	public ResponseEntity<?> addTask(@RequestBody TaskDto taskDto){
		String add = taskService.addTask(taskDto);
		return new ResponseEntity(add,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> geTaskById(@PathVariable("id") String id){
		TaskDto taskDtos = taskService.getTaskById(Integer.parseInt(id));
		return new ResponseEntity(taskDtos,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> geAllTasks(){
		List<TaskDto> taskDtos = taskService.getTask();
		return new ResponseEntity(taskDtos,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> geAllTasksByUserId(@PathVariable("id") String id){
		
		List<TaskDto> taskDtos = taskService.getByUser(id);
		return new ResponseEntity(taskDtos,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@RequestBody TaskDto taskDto,@PathVariable("id") String id){
		TaskDto taDto = taskService.updateTaskById(Integer.parseInt(id),taskDto);
		return new ResponseEntity(taDto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") String id){
		
		String data = taskService.deleteTaskById(Integer.parseInt(id));
		return new ResponseEntity(data,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<?> updateStatus(@RequestBody TaskDto  isDone,@PathVariable("id") String id){
		
		TaskDto taDto = taskService.updateTaskByisDone(Integer.parseInt(id),isDone.getIsDone());
		return new ResponseEntity(taDto,HttpStatus.ACCEPTED);
	}
}
