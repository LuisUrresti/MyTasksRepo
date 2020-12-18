package com.taskapp.service;

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
	public Task save(final Task task) {
		// Se llama al repositorio y se guarda el objeto task
		repository.save(task);
		return task;
	}
	
	// Get all task
	public List<Task> getAll(){
		// Se crea una lista de tasks
		final List<Task> tasks = new ArrayList<>();
		// Se coge de la base de datos todas las tareas
		repository.findAll().forEach(task->tasks.add(task));
		// Se devuelven las tareas
		return tasks;
	}
	
	// Delete task
	public void deleteById(int taskId) {
		// Se llama al repositorio para eliminar la tarea en función de su ID conocido
		repository.deleteById(taskId);
	}
	
	// Update task
	public Task update(Task task, int id) {
		// A partir del ID se busca la tarea que se quiere actualizar
		Task old = repository.findById(id).get();
		// Se actualizan los valores de la tarea
		old.setTaskDescription(task.getTaskDescription());
		old.setTaskState(task.getTaskState().toString());
		// Se guarda la nueva tarea
		repository.save(old);
		
		return old;
	}
	
	// Find by state
	public List<Task> findByState(String state) {
		// Se crea una lista de tareas
		final List<Task> tasks = new ArrayList<Task>();
		// Se cogen todas las tareas que haya en la base de datos
		repository.findAll().forEach(task->tasks.add(task));
		// Se crea una lista auxiliar
		List<Task> taskState = new ArrayList<Task>();
		// Se recorre la lista de tareas y se compara el estado de cada tarea con el buscado, si coincide se guarda la tarea en la
		// lista auxiliar
		for(Task task : tasks) {
			if(task.getTaskState().contentEquals(state)) {
				System.out.println("Descripcion: "+task.getTaskDescription()+"Estado: "+task.getTaskState());
				taskState.add(task);
			}	
		}
		return taskState;
	}

}
