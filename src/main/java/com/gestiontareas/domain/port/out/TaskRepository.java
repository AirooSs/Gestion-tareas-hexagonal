package com.gestiontareas.domain.port.out;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.project.ProjectId;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de tareas.
 * 
 * Es una interfaz en el dominio — no sabe nada de JPA ni de PostgreSQL.
 * La implementación real (JpaTaskRepository) vivirá en infrastructure/persistence.
 * 
 * Esto es lo que permite que el dominio no dependa de la base de datos.
 */

//CRUD
public interface TaskRepository {

    /** Guarda una tarea nueva o actualiza una existente */
    Task save(Task task);

    /** Busca una tarea por su ID */
    Optional<Task> findById(TaskId id);

    /** Devuelve todas las tareas de un proyecto */
    List<Task> findByProjectId(ProjectId projectId);

    /** Elimina una tarea por su ID */
    void deleteById(TaskId id);
}