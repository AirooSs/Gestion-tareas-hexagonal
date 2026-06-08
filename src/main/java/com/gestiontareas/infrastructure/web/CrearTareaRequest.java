package com.gestiontareas.infrastructure.web;

import java.util.UUID;

/**
 * DTO de entrada para crear una tarea.
 * Record porque es inmutable y solo transporta datos.
 */

public record CrearTareaRequest(String titulo, String descripcion, UUID projectId) {}