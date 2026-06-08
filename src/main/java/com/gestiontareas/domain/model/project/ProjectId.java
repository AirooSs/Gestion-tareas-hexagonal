package com.gestiontareas.domain.model.project;

import java.util.UUID;

/**
 * Value Object que representa el identificador único de un proyecto.
 * Mismo patrón que TaskId — record inmutable con UUID.
 */

public record ProjectId(UUID value) {

    public static ProjectId generate() {
        return new ProjectId(UUID.randomUUID());
    }

    public static ProjectId of(UUID value) {
        return new ProjectId(value);
    }
}