package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.model.user.User;

import java.util.UUID;

/**
 * DTO de respuesta para un usuario.
 */

public record UsuarioResponse(
    UUID id,
    String name,
    String email
) {
    public static UsuarioResponse from(User user) {
        return new UsuarioResponse(
            user.getId().value(),
            user.getName(),
            user.getEmail().value()
        );
    }
}