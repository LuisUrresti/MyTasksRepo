package com.taskapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskapp.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{}
