package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.port.in.CrearTareaUseCase;
import com.gestiontareas.domain.port.out.TaskRepository;

/**
 * Implementación del caso de uso CrearTareaUseCase.
 * 
 * Orquesta el dominio: crea la tarea y la persiste a través del puerto de
 * salida. No sabe nada de HTTP ni de JPA — solo habla con el dominio.
 */

public class CrearTareaService implements CrearTareaUseCase {

	private final TaskRepository taskRepository;

	public CrearTareaService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task ejecutar(String titulo, String descripcion, ProjectId projectId) {

		Task tarea = new Task(TaskId.generate(), titulo, descripcion, projectId);

		return taskRepository.save(tarea);
	}

}
