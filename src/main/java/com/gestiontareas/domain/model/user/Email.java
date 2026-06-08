package com.gestiontareas.domain.model.user;

/**
 * Value Object que representa el email de un usuario.
 * Valida el formato en el constructor para garantizar
 * que nunca exista un Email inválido en el dominio.
 */
public record Email(String value) {

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!value.contains("@")) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
    }
}