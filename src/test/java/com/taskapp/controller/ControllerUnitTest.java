package com.taskapp.controller;

import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskapp.model.Task;
import com.taskapp.service.TaskService;

@WebMvcTest(controllers = TaskController.class)
public class ControllerUnitTest {
	
	@Autowired // Permite simular el control
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private List<Task> taskList;

    @BeforeEach
    void setUp() {
        this.taskList = new ArrayList<>();
        this.taskList.add(new Task("tarea_1", "Pendiente"));
        this.taskList.add(new Task("tarea_2", "Completado"));
        this.taskList.add(new Task("tarea_3", "En curso"));

       // objectMapper.registerModule(new Module());
       // objectMapper.registerModule(new ConstraintViolationProblemModule());
    }
    
    @Test
    void testControllerGetAll() throws Exception {

        given(service.getAll()).willReturn(taskList);

        this.mockMvc.perform(get("/task/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(taskList.size())));
    }
    
    @Test
    void testControllerSave() throws Exception {
    	
        given(service.save((Task) any(Task.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Task task = new Task("tarea_4", "Pendiente");

        this.mockMvc.perform(post("/task/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskDescription", is(task.getTaskDescription())))
                .andExpect(jsonPath("$.taskState", is(task.getTaskState())));

    }
    
    

}
