package com.gestiontareas.domain.model.project;

import com.gestiontareas.domain.model.user.UserId;

/**
 * Entidad principal del dominio Project.
 * Representa un proyecto que agrupa tareas.
 * 
 * Clase Java pura, sin Spring ni JPA.
 */

public class Project {

    private final ProjectId id;
    private String name;
    private String description;
    private final UserId ownerId;

    public Project(ProjectId id, String name, String description, UserId ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

    /** Actualiza el nombre del proyecto */
    public void renombrar(String nuevoNombre) {
        this.name = nuevoNombre;
    }

    public ProjectId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public UserId getOwnerId() { return ownerId; }
}