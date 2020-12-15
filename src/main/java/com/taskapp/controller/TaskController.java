package com.taskapp.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskapp.TaskService;
import com.taskapp.model.Task;


@RestController
public class TaskController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TaskService service;
	
	// Save student entity in the h2 database.
    // @PostMapping annotation handles the http post request matched with the given uri.
    // @RequestBody annotation binds the http request body to the domain object.
    // @Valid annotation validates a model after binding the user input to it.
    @PostMapping(value = "/task/save")
    public int save(final @RequestBody @Valid Task task) {
        log.info("Guardando tarea en la base de datos");
        service.save(task);
        return task.getId();
    }  
    
    @PostMapping(value = "/task/update{id}")
    public int update(final @RequestBody @Valid Task task, @PathVariable String id ) {
    	log.info("Actualizando una tarea");
    	int taskId = Integer.parseInt(id);
    	service.update(task, taskId);
    	return task.getId();
	}
    
    @GetMapping(value = "task/getall")
    public List<Task> getAll(){
    	log.info("Mostrando todas las tareas");
    	return service.getAll();
    }
    
    @DeleteMapping(value = "task/delete{id}")
    public void delete(@PathVariable String id) {
    	log.info("Eliminando tarea");
    	int taskId = Integer.parseInt(id);
    	service.deleteById(taskId);
    }
    
    @GetMapping(value = "task/Estado_{state}")
    public List<Task> findByState(@PathVariable String state) {
    	log.info("Filtrando por estado");
    	return service.findByState(state);
    }
   

}
