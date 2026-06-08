package com.gestiontareas.infrastructure.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO de entrada para crear una tarea.
 * 
 * @NotBlank valida que el campo no sea nulo ni vacío.
 * @NotNull valida que el campo no sea nulo.
 */

public record CrearTareaRequest(@NotBlank(message = "El título no puede estar vacío") String titulo, String descripcion,
		@NotNull(message = "El projectId es obligatorio") UUID projectId) {
}