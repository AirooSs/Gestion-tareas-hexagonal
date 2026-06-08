package com.gestiontareas.infrastructure.persistence;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.task.TaskStatus;
import com.gestiontareas.domain.model.user.UserId;

/**
 * Convierte entre Task (dominio) y TaskEntity (infraestructura).
 * 
 * Es el traductor entre las dos capas. El dominio nunca ve TaskEntity
 * y la infraestructura nunca debería manipular Task directamente.
 */

public class TaskMapper {

	/** Convierte una Task del dominio a TaskEntity para persistir en BD */
    public static TaskEntity toEntity(Task task) {
        return new TaskEntity(
            task.getId().value(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus().name(),
            task.getProjectId().value(),
            task.getAssignedTo() != null ? task.getAssignedTo().value() : null
        );
    }
    
    /** Convierte una TaskEntity de BD a Task del dominio */
    public static Task toDomain(TaskEntity entity) {
        Task task = new Task(
            TaskId.of(entity.getId()),
            entity.getTitulo(),
            entity.getDescripcion(),
            ProjectId.of(entity.getProjectId())
        );

        task.cambiarEstado(TaskStatus.valueOf(entity.getStatus()));

        if (entity.getAssignedTo() != null) {
            task.asignarA(UserId.of(entity.getAssignedTo()));
        }

        return task;
    }
}
