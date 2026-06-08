package com.gestiontareas.infrastructure.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO de entrada para crear un proyecto.
 */

public record CrearProyectoRequest(@NotBlank(message = "El nombre no puede estar vacío") String name,
		String description, @NotNull(message = "El ownerId es obligatorio") UUID ownerId) {
}