package com.gestiontareas.domain.port.in;

import java.util.List;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;

public interface ListarTareasPorProyectoUseCase {

	List<Task> ejecutar(ProjectId projectId);
}
