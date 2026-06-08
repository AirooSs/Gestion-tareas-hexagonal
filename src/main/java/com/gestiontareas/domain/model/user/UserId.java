package com.gestiontareas.domain.model.user;

import java.util.UUID;

/**
 * Value Object que representa el identificador único de un usuario.
 * Mismo patrón que TaskId y ProjectId — record inmutable con UUID.
 */

public record UserId(UUID value) {

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }
}