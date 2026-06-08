package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.task.Task;

import java.util.UUID;

/**
 * DTO de respuesta para una tarea.
 * Aplana los Value Objects del dominio a tipos simples.
 */

public record TareaResponse(
    UUID id,
    String titulo,
    String descripcion,
    String status,
    UUID projectId,
    UUID assignedTo
) {
    public static TareaResponse from(Task task) {
        return new TareaResponse(
            task.getId().value(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus().name(),
            task.getProjectId().value(),
            task.getAssignedTo() != null ? task.getAssignedTo().value() : null
        );
    }
}