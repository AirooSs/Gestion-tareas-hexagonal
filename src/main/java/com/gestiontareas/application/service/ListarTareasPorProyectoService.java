package com.gestiontareas.application.service;

import java.util.List;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.port.in.ListarTareasPorProyectoUseCase;
import com.gestiontareas.domain.port.out.TaskRepository;

/**
 * Implementación del caso de uso ListarTareasPorProyectoUseCase.
 * 
 * Devuelve todas las tareas asociadas a un proyecto.
 */

public class ListarTareasPorProyectoService implements ListarTareasPorProyectoUseCase {

	private final TaskRepository taskRepository;

	public ListarTareasPorProyectoService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> ejecutar(ProjectId projectId) {
		// TODO Auto-generated method stub
		return taskRepository.findByProjectId(projectId);
	}

}
