package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.task.TaskStatus;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.port.in.ActualizarEstadoTareaUseCase;
import com.gestiontareas.domain.port.in.CrearTareaUseCase;
import com.gestiontareas.domain.port.in.ListarTareasPorProyectoUseCase;
import com.gestiontareas.domain.port.in.ObtenerTareaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Adaptador de entrada — expone los casos de uso como endpoints REST.
 * 
 * El controller solo llama a los puertos de entrada (interfaces).
 * Nunca llama directamente a los servicios.
 */

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    private final CrearTareaUseCase crearTareaUseCase;
    private final ObtenerTareaUseCase obtenerTareaUseCase;
    private final ActualizarEstadoTareaUseCase actualizarEstadoTareaUseCase;
    private final ListarTareasPorProyectoUseCase listarTareasPorProyectoUseCase;

    public TaskController(
        CrearTareaUseCase crearTareaUseCase,
        ObtenerTareaUseCase obtenerTareaUseCase,
        ActualizarEstadoTareaUseCase actualizarEstadoTareaUseCase,
        ListarTareasPorProyectoUseCase listarTareasPorProyectoUseCase
    ) {
        this.crearTareaUseCase = crearTareaUseCase;
        this.obtenerTareaUseCase = obtenerTareaUseCase;
        this.actualizarEstadoTareaUseCase = actualizarEstadoTareaUseCase;
        this.listarTareasPorProyectoUseCase = listarTareasPorProyectoUseCase;
    }

    @PostMapping
    public ResponseEntity<Task> crearTarea(@RequestBody CrearTareaRequest request) {
        Task tarea = crearTareaUseCase.ejecutar(
            request.titulo(),
            request.descripcion(),
            ProjectId.of(request.projectId())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(tarea);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> obtenerTarea(@PathVariable UUID id) {
        Task tarea = obtenerTareaUseCase.ejecutar(TaskId.of(id));
        return ResponseEntity.ok(tarea);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Task> actualizarEstado(
        @PathVariable UUID id,
        @RequestBody ActualizarEstadoRequest request
    ) {
        Task tarea = actualizarEstadoTareaUseCase.ejecutar(
            TaskId.of(id),
            TaskStatus.valueOf(request.estado())
        );
        return ResponseEntity.ok(tarea);
    }

    @GetMapping("/proyecto/{projectId}")
    public ResponseEntity<List<Task>> listarPorProyecto(@PathVariable UUID projectId) {
        List<Task> tareas = listarTareasPorProyectoUseCase.ejecutar(ProjectId.of(projectId));
        return ResponseEntity.ok(tareas);
    }
}