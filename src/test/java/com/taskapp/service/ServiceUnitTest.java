package com.taskapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskapp.controller.TaskController;
import com.taskapp.model.Task;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceUnitTest {
	
	@InjectMocks // Te remplaza los autowired del controller por mocks
	TaskController controller;
	
	@Mock
	TaskService service;

	
//	@Test
//	public void testServiceSave() {
//		
//		final Task task = new Task("Tarea_1","Pendiente");
//        
//        int taskId = task.getId();
//        
//        // Cuando se llame a esta instrucción lo sustituyes por lo del thenReturn
//        when(controller.save(task)).thenReturn(taskId);
//        
//        assertThat(taskId).isNotNull();
//        assertThat(taskId).isEqualTo(task.getId());
//	}
	
	@Test
	public void testServiceGetAll() {
		
		Task task1 = new Task("Task_1","Pendiente");
		Task task2 = new Task("Task_2","En curso");
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		
		when(service.getAll()).thenReturn(tasks);
		
		//when
		List<Task> result = service.getAll();
		
		//then
	    assertThat(result.size()).isEqualTo(2);
	  
	    assertThat(result.get(0).getTaskDescription()).isEqualTo(task1.getTaskDescription());
	    assertThat(result.get(1).getTaskDescription()).isEqualTo(task2.getTaskDescription());	
	}
	
	
	@Test
	public void testServiceUpdate() {
		
		Task task = new Task("Tarea_1","Pendiente");
		Task newTask = new Task("Tarea_1", "En curso");
		
		controller.save(task);

		String desc = newTask.getTaskDescription();
		String sta  = newTask.getTaskState();
		
		controller.update(newTask, String.valueOf(task.getId()));
		
		assertThat(newTask.getTaskDescription()).isEqualTo(desc);
		assertThat(newTask.getTaskState()).isEqualTo(sta);
	}
	
	@Test
	public void testServiceFindState() {
		
		Task task1 = new Task("Task_1","Pendiente");
		Task task2 = new Task("Task_2","En curso");
		Task task3 = new Task("Task_3", "Pendiente");
		
		service.save(task1);
		service.save(task2);
		service.save(task3);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task3);
		
		when(service.findByState("Pendiente")).thenReturn(tasks);
		
		List<Task> result = service.findByState("Pendiente");
		
		//then
	    assertThat(result.size()).isEqualTo(2);
	  
	    assertThat(result.get(0).getTaskState()).isEqualTo(task1.getTaskState());
	    assertThat(result.get(1).getTaskState()).isEqualTo(task3.getTaskState());	
	}
	
	@Test
	public void testServiceDelete() {
		
		Task task1 = new Task("Task_1","Pendiente");
		Task task2 = new Task("Task_2","En curso");
		Task task3 = new Task("Task_3", "Pendiente");
		
		service.save(task1);
		service.save(task2);
		service.save(task3);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task2);
		tasks.add(task3);
		
		service.deleteById(task1.getId());
		
		when(service.getAll()).thenReturn(tasks);
		
		List<Task> result = service.getAll();
		
	    assertThat(result.size()).isEqualTo(2);
		  
	    assertThat(result.get(0).getTaskState()).isEqualTo(task2.getTaskState());
	    assertThat(result.get(1).getTaskState()).isEqualTo(task3.getTaskState());	
	    assertThat(result.get(0).getTaskDescription()).isNotEqualTo(task1.getTaskDescription());
	    assertThat(result.get(1).getTaskDescription()).isNotEqualTo(task1.getTaskDescription());
	}
}