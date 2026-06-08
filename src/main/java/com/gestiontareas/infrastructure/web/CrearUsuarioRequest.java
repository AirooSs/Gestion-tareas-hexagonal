package com.gestiontareas.infrastructure.web;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de entrada para crear un usuario.
 */

public record CrearUsuarioRequest(
    @NotBlank(message = "El nombre no puede estar vacío") String name,
    @NotBlank(message = "El email no puede estar vacío") String email
) {}