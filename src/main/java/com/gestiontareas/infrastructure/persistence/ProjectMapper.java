package com.gestiontareas.infrastructure.persistence;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.user.UserId;

/**
 * Convierte entre Project (dominio) y ProjectEntity (infraestructura).
 */

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project) {
        return new ProjectEntity(
            project.getId().value(),
            project.getName(),
            project.getDescription(),
            project.getOwnerId().value()
        );
    }

    public static Project toDomain(ProjectEntity entity) {
        return new Project(
            ProjectId.of(entity.getId()),
            entity.getName(),
            entity.getDescription(),
            UserId.of(entity.getOwnerId())
        );
    }
}