package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.CrearProyectoUseCase;
import com.gestiontareas.domain.port.in.ListarProyectosPorUsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Adaptador de entrada — expone los casos de uso de Project como endpoints REST.
 */

@RestController
@RequestMapping("/api/proyectos")
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

    @PostMapping
    public ResponseEntity<Project> crearProyecto(@RequestBody CrearProyectoRequest request) {
        Project proyecto = crearProyectoUseCase.ejecutar(
            request.name(),
            request.description(),
            UserId.of(request.ownerId())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(proyecto);
    }

    @GetMapping("/usuario/{ownerId}")
    public ResponseEntity<List<Project>> listarPorUsuario(@PathVariable UUID ownerId) {
        List<Project> proyectos = listarProyectosPorUsuarioUseCase.ejecutar(UserId.of(ownerId));
        return ResponseEntity.ok(proyectos);
    }
}