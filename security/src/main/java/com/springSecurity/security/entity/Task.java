package com.springSecurity.security.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String taskName;
	private String description;
	private LocalDate date;
	private LocalTime time;
	private Boolean isDone;
	@ManyToOne
	@JoinColumn(name="assignedTo")
	private User assignedTo;
	private LocalDate deadLineDate;
	private LocalTime deadLineTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	
	public Task( String taskName, String description, LocalDate date, LocalTime time, Boolean isDone,
			User assignedTo, LocalDate deadLineDate, LocalTime deadLineTime) {
		super();
		
		this.taskName = taskName;
		this.description = description;
		this.date = date;
		this.time = time;
		this.isDone = isDone;
		this.assignedTo = assignedTo;
		this.deadLineDate = deadLineDate;
		this.deadLineTime = deadLineTime;
	}
	public User getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
	public LocalDate getDeadLineDate() {
		return deadLineDate;
	}
	public void setDeadLineDate(LocalDate deadLineDate) {
		this.deadLineDate = deadLineDate;
	}
	public LocalTime getDeadLineTime() {
		return deadLineTime;
	}
	public void setDeadLineTime(LocalTime deadLineTime) {
		this.deadLineTime = deadLineTime;
	}
	public Task() {
		super();
	}
	
	
	

}
