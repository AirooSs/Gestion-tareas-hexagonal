package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.user.UserId;

import java.util.List;

/**
 * Puerto de entrada para listar proyectos de un usuario.
 */

public interface ListarProyectosPorUsuarioUseCase {

    List<Project> ejecutar(UserId ownerId);
}