package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.CrearProyectoUseCase;
import com.gestiontareas.domain.port.in.ListarProyectosPorUsuarioUseCase;
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
 * Adaptador de entrada — expone los casos de uso de Project como endpoints REST.
 * El controller solo llama a los puertos de entrada (interfaces).
 * Nunca llama directamente a los servicios.
 */

@RestController
@RequestMapping("/api/proyectos")
@Tag(name = "Proyectos", description = "Operaciones sobre proyectos")
public class ProjectController {

    private final CrearProyectoUseCase crearProyectoUseCase;
    private final ListarProyectosPorUsuarioUseCase listarProyectosPorUsuarioUseCase;

    public ProjectController(
        CrearProyectoUseCase crearProyectoUseCase,
        ListarProyectosPorUsuarioUseCase listarProyectosPorUsuarioUseCase
    ) {
        this.crearProyectoUseCase = crearProyectoUseCase;
        this.listarProyectosPorUsuarioUseCase = listarProyectosPorUsuarioUseCase;
    }

    /** Crea un nuevo proyecto asociado a un usuario */
    @Operation(summary = "Crear proyecto", description = "Crea un nuevo proyecto asociado a un usuario")
    @PostMapping
    public ResponseEntity<ProyectoResponse> crearProyecto(@Valid @RequestBody CrearProyectoRequest request) {
        ProyectoResponse response = ProyectoResponse.from(crearProyectoUseCase.ejecutar(
            request.name(),
            request.description(),
            UserId.of(request.ownerId())
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /** Lista todos los proyectos de un usuario */
    @Operation(summary = "Listar proyectos por usuario", description = "Devuelve todos los proyectos de un usuario")
    @GetMapping("/usuario/{ownerId}")
    public ResponseEntity<List<ProyectoResponse>> listarPorUsuario(@PathVariable UUID ownerId) {
        List<ProyectoResponse> response = listarProyectosPorUsuarioUseCase.ejecutar(UserId.of(ownerId))
            .stream()
            .map(ProyectoResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}