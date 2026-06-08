package com.gestiontareas.domain.model.task;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.user.UserId;

/**
 * Entidad principal del dominio Task.
 * Representa una tarea dentro de un proyecto.
 * 
 * Es una clase Java pura, sin anotaciones de Spring ni JPA.
 * Contiene las reglas de negocio relacionadas con una tarea.
 */
public class Task {

    private final TaskId id;
    private String title;
    private String description;
    private TaskStatus status;
    private final ProjectId projectId;
    private UserId assignedTo;

    public Task(TaskId id, String title, String description, ProjectId projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDIENTE;
        this.projectId = projectId;
        this.assignedTo = null;
    }

    /** Cambia el estado de la tarea */
    public void cambiarEstado(TaskStatus nuevoEstado) {
        this.status = nuevoEstado;
    }

    /** Asigna la tarea a un usuario */
    public void asignarA(UserId usuarioId) {
        this.assignedTo = usuarioId;
    }

    public TaskId getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public ProjectId getProjectId() { return projectId; }
    public UserId getAssignedTo() { return assignedTo; }
}