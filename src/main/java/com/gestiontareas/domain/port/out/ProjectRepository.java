package com.gestiontareas.domain.port.out;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de proyectos.
 */

public interface ProjectRepository {

    Project save(Project project);

    Optional<Project> findById(ProjectId id);

    /** Devuelve todos los proyectos de un usuario */
    List<Project> findByOwnerId(UserId ownerId);

    void deleteById(ProjectId id);
}