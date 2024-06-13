package com.springSecurity.security.mapper;

import com.springSecurity.security.dto.TaskDto;
import com.springSecurity.security.entity.Task;
import com.springSecurity.security.entity.User;

public class TaskMapper {
	public static TaskDto mapToTaskDto(Task task) {
		TaskDto taskDto = new TaskDto(task.getTaskName(),task.getDescription(),task.getDate(),task.getTime(),task.getIsDone(),task.getAssignedTo().getId().toString(),task.getDeadLineDate(),task.getDeadLineTime());
		taskDto.setId(task.getId());
		return taskDto;
	}
	
	public static Task mapToTask(TaskDto task,User user) {
		return new Task(task.getTaskName(),task.getDescription(),task.getDate(),task.getTime(),task.getIsDone(),user,task.getDeadLineDate(),task.getDeadLineTime());
	}
	
	
}
