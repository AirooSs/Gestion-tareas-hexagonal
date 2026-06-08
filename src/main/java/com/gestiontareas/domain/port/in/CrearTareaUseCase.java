package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;

/**
 * Puerto de entrada para el caso de uso de crear una tarea.
 * 
 * Define el contrato que la capa de aplicación debe implementar.
 * El controller REST llamará a esta interfaz, nunca directamente al servicio.
 */

public interface CrearTareaUseCase {

	Task ejecutar(String titulo, String descripcion, ProjectId projectId);
}
