package com.gestiontareas.domain.model.task;

import java.util.UUID;

/**
 * Value Object que representa el identificador único de una tarea.
 * Uso UUID para garantizar unicidad sin depender de la base de datos.
 * 
 * - generate(): crea un ID nuevo aleatorio (al crear una tarea nueva)
 * - of(uuid): reconstruye el ID desde un UUID existente (al recuperar de BD)
 * 
 * Usamos record porque TaskId es inmutable, Java genera automáticamente
 * el constructor, getter, equals y hashCode.
 * record es nativo de Java desde la versión 16.
 */

public record TaskId(UUID value) {

    public static TaskId generate() {
        return new TaskId(UUID.randomUUID());
    }

    public static TaskId of(UUID value) {
        return new TaskId(value);
    }
}