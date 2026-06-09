package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.task.TaskStatus;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.port.in.ActualizarEstadoTareaUseCase;
import com.gestiontareas.domain.port.in.CrearTareaUseCase;
import com.gestiontareas.domain.port.in.ListarTareasPorProyectoUseCase;
import com.gestiontareas.domain.port.in.ObtenerTareaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adaptador de entrada — expone los casos de uso como endpoints REST.
 * El controller solo llama a los puertos de entrada (interfaces).
 * Nunca llama directamente a los servicios.
 */

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "Operaciones sobre tareas")
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

    /** Crea una nueva tarea asociada a un proyecto */
    @Operation(summary = "Crear tarea", description = "Crea una nueva tarea asociada a un proyecto")
    @PostMapping
    public ResponseEntity<TareaResponse> crearTarea(@Valid @RequestBody CrearTareaRequest request) {
        TareaResponse response = TareaResponse.from(crearTareaUseCase.ejecutar(
            request.titulo(),
            request.descripcion(),
            ProjectId.of(request.projectId())
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /** Obtiene una tarea por su ID */
    @Operation(summary = "Obtener tarea", description = "Obtiene una tarea por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> obtenerTarea(@PathVariable UUID id) {
        TareaResponse response = TareaResponse.from(obtenerTareaUseCase.ejecutar(TaskId.of(id)));
        return ResponseEntity.ok(response);
    }

    /** Actualiza el estado de una tarea */
    @Operation(summary = "Actualizar estado", description = "Actualiza el estado de una tarea")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TareaResponse> actualizarEstado(
        @PathVariable UUID id,
        @RequestBody ActualizarEstadoRequest request
    ) {
        TareaResponse response = TareaResponse.from(actualizarEstadoTareaUseCase.ejecutar(
            TaskId.of(id),
            TaskStatus.valueOf(request.estado())
        ));
        return ResponseEntity.ok(response);
    }

    /** Lista todas las tareas de un proyecto */
    @Operation(summary = "Listar tareas por proyecto", description = "Devuelve todas las tareas de un proyecto")
    @GetMapping("/proyecto/{projectId}")
    public ResponseEntity<List<TareaResponse>> listarPorProyecto(@PathVariable UUID projectId) {
        List<TareaResponse> response = listarTareasPorProyectoUseCase.ejecutar(ProjectId.of(projectId))
            .stream()
            .map(TareaResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}