package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.user.UserId;

/**
 * Puerto de entrada para crear un proyecto.
 */

public interface CrearProyectoUseCase {

    Project ejecutar(String name, String description, UserId ownerId);
}