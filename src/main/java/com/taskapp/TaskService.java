package com.taskapp;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskapp.model.Task;
import com.taskapp.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	// Save task
	public void save(final Task task) {
		repository.save(task);
	}
	
	// Get all task
	public List<Task> getAll(){
		
		final List<Task> tasks = new ArrayList<>();
		repository.findAll().forEach(task->tasks.add(task));
		
		return tasks;
	}
	
	// Delete task
	public void deleteById(int taskId) {
		
		repository.deleteById(taskId);
	}
	
	// Update task
	public void update(Task task, int id) {
		
		Task old = repository.findById(id).get();
		
		
		old.setTaskDescription(task.getTaskDescription());
		old.setTaskState(task.getTaskState().toString());
		
		repository.save(old);
		
	}
	
	// Find by state
	public List<Task> findByState(String state) {
		
		final List<Task> tasks = new ArrayList<Task>();
		repository.findAll().forEach(task->tasks.add(task));
		
		List<Task> taskState = new ArrayList<Task>();
		
		for(Task task : tasks) {
			if(task.getTaskState().contentEquals(state)) {
				System.out.println("Descripcion: "+task.getTaskDescription()+"Estado: "+task.getTaskState());
				taskState.add(task);
			}
				
		}

		return taskState;
	}

}
