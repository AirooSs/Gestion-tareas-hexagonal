package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.task.TaskStatus;
import com.gestiontareas.domain.port.in.ActualizarEstadoTareaUseCase;
import com.gestiontareas.domain.port.out.TaskRepository;

/**
 * Implementación del caso de uso ActualizarEstadoTareaUseCase.
 * 
 * Recupera la tarea, delega el cambio de estado en la propia entidad
 * y la vuelve a persistir. La regla de negocio vive en Task.cambiarEstado().
 */

public class ActualizarEstadoTareaService implements ActualizarEstadoTareaUseCase {

	private final TaskRepository taskRepository;

	public ActualizarEstadoTareaService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task ejecutar(TaskId id, TaskStatus nuevoEstado) {
		// TODO Auto-generated method stub
		Task tarea = taskRepository.findById(id)
				.orElseThrow(() ->
				new RuntimeException("Tarea no encontrada con id: " + id.value()));

		tarea.cambiarEstado(nuevoEstado);

		return taskRepository.save(tarea);
	}

}
