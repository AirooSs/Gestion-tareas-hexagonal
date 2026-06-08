package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.port.in.ObtenerTareaUseCase;
import com.gestiontareas.domain.port.out.TaskRepository;

public class ObtenerTareaService implements ObtenerTareaUseCase {

	private final TaskRepository taskRepository;

	public ObtenerTareaService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task ejecutar(TaskId id) {
		// TODO Auto-generated method stub
		return taskRepository.findById(id)
				.orElseThrow(() ->
				new RuntimeException("Tarea no encontrada con id: " + id.value()));
	}

}
