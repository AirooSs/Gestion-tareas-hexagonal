package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.task.TaskStatus;

public interface ActualizarEstadoTareaUseCase {

	Task ejecutar(TaskId id, TaskStatus nuevoEstado);
}
