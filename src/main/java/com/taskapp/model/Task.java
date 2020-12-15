package com.taskapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Entity indica a JPA que esta clase es una entidad
@Entity
public class Task {
	
	// Se indica que el id se genera de forma automática
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	// Se indica la columna "Descripción" que indica la descripción de la tarea y tiene 140 caracteres como máximo
	@Column(name = "Descripcion", length = 140, nullable = false, unique = false)
	private String taskDescription;
	// Se indica la columna "Estado" que indica el estado de la tarea
	@Column(name = "Estado", length = 140, nullable = false, unique = false)
	private String taskState;
	
	public Task() {
		
	}
	
	public Task(String taskDescription, String taskState) {
		
		this.taskDescription = taskDescription;
		this.taskState = taskState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
		result = prime * result + ((taskState == null) ? 0 : taskState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id != other.id)
			return false;
		if (taskDescription == null) {
			if (other.taskDescription != null)
				return false;
		} else if (!taskDescription.equals(other.taskDescription))
			return false;
		if (taskState == null) {
			if (other.taskState != null)
				return false;
		} else if (!taskState.equals(other.taskState))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskDescription=" + taskDescription + ", taskState="
				+ taskState + "]";
	}
	
	
	
	
}
