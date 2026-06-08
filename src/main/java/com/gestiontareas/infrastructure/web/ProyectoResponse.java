package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.project.Project;

import java.util.UUID;

/**
 * DTO de respuesta para un proyecto.
 */

public record ProyectoResponse(
    UUID id,
    String name,
    String description,
    UUID ownerId
) {
    public static ProyectoResponse from(Project project) {
        return new ProyectoResponse(
            project.getId().value(),
            project.getName(),
            project.getDescription(),
            project.getOwnerId().value()
        );
    }
}