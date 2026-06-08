package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.CrearUsuarioUseCase;
import com.gestiontareas.domain.port.in.ObtenerUsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Adaptador de entrada — expone los casos de uso de User como endpoints REST.
 */

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

	private final CrearUsuarioUseCase crearUsuarioUseCase;
	private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;

	public UserController(CrearUsuarioUseCase crearUsuarioUseCase, ObtenerUsuarioUseCase obtenerUsuarioUseCase) {
		this.crearUsuarioUseCase = crearUsuarioUseCase;
		this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
	}

	@PostMapping
	public ResponseEntity<User> crearUsuario(@RequestBody CrearUsuarioRequest request) {
		User usuario = crearUsuarioUseCase.ejecutar(request.name(), new Email(request.email()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> obtenerUsuario(@PathVariable UUID id) {
		User usuario = obtenerUsuarioUseCase.ejecutar(UserId.of(id));
		return ResponseEntity.ok(usuario);
	}
}