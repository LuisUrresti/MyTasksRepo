package com.taskapp.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.taskapp.model.Task;
import com.taskapp.service.TaskService;


@RestController
@CrossOrigin(origins = "*")
public class TaskController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TaskService service;
	
	// Se guarda la tarea en la base de datos
    // @PostMapping annotation handles the http post request matched with the given uri.
    // @RequestBody annotation binds the http request body to the domain object.
    // @Valid annotation validates a model after binding the user input to it.
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity<Task> save(final @RequestBody @Valid Task task) {
        log.info("Guardando tarea en la base de datos");
        Task taskSaved = service.save(task);
		return new ResponseEntity<Task>(taskSaved, HttpStatus.CREATED);
    }  
    
    // Se actualiza una tarea que ya este en la base de datos
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> update(final @RequestBody @Valid Task task, @PathVariable String id ) {
    	log.info("Actualizando una tarea");
    	int taskId = Integer.parseInt(id);
    	Task taskUpdated = service.update(task, taskId);
    	return new ResponseEntity<Task>(taskUpdated, HttpStatus.OK);
	}
    
    // Se muestran todas las tareas de la base de datos
    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public List<Task> getAll(){
    	//log.info("Mostrando todas las tareas");
    	List<Task> tasks = service.getAll();
    	return tasks;
    }
    
    // Se elimina una tarea que este en la base de datos
    @RequestMapping(value = "tasks/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
    	log.info("Eliminando tarea");
    	int taskId = Integer.parseInt(id);
    	service.deleteById(taskId);
    }
    
    // Se mira cuantas tareas estan en el estado {state} en la base de datos y se muetran
    @RequestMapping(value = "tasks/{state}", method = RequestMethod.GET)
    public List<Task> findByState(@PathVariable String state) {
    	log.info("Filtrando por estado");
    	return service.findByState(state);
    }
   

}