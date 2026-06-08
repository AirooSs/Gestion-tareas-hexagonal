package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.CrearProyectoUseCase;
import com.gestiontareas.domain.port.out.ProjectRepository;

/**
 * Implementación del caso de uso CrearProyectoUseCase.
 */

public class CrearProyectoService implements CrearProyectoUseCase {

    private final ProjectRepository projectRepository;

    public CrearProyectoService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project ejecutar(String name, String description, UserId ownerId) {
        Project proyecto = new Project(
            ProjectId.generate(),
            name,
            description,
            ownerId
        );
        return projectRepository.save(proyecto);
    }
}