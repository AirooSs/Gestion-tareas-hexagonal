package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.ListarProyectosPorUsuarioUseCase;
import com.gestiontareas.domain.port.out.ProjectRepository;

import java.util.List;

/**
 * Implementación del caso de uso ListarProyectosPorUsuarioUseCase.
 */

public class ListarProyectosPorUsuarioService implements ListarProyectosPorUsuarioUseCase {

    private final ProjectRepository projectRepository;

    public ListarProyectosPorUsuarioService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> ejecutar(UserId ownerId) {
        return projectRepository.findByOwnerId(ownerId);
    }
}