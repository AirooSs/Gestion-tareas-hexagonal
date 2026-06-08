package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;

/**
 * Puerto de entrada para obtener una tarea por su ID.
 */

public interface ObtenerTareaUseCase {

	Task ejecutar(TaskId id);
}
