package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.CrearUsuarioUseCase;
import com.gestiontareas.domain.port.in.ObtenerUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Adaptador de entrada — expone los casos de uso de User como endpoints REST.
 * El controller solo llama a los puertos de entrada (interfaces).
 * Nunca llama directamente a los servicios.
 */

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UserController {

    private final CrearUsuarioUseCase crearUsuarioUseCase;
    private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;

    public UserController(
        CrearUsuarioUseCase crearUsuarioUseCase,
        ObtenerUsuarioUseCase obtenerUsuarioUseCase
    ) {
        this.crearUsuarioUseCase = crearUsuarioUseCase;
        this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
    }

    /** Crea un nuevo usuario */
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en el sistema")
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody CrearUsuarioRequest request) {
        UsuarioResponse response = UsuarioResponse.from(crearUsuarioUseCase.ejecutar(
            request.name(),
            new Email(request.email())
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /** Obtiene un usuario por su ID */
    @Operation(summary = "Obtener usuario", description = "Obtiene un usuario por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(@PathVariable UUID id) {
        UsuarioResponse response = UsuarioResponse.from(obtenerUsuarioUseCase.ejecutar(UserId.of(id)));
        return ResponseEntity.ok(response);
    }
}