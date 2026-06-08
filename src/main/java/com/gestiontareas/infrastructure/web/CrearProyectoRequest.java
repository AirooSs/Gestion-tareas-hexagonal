package com.gestiontareas.infrastructure.web;

import java.util.UUID;

/**
 * DTO de entrada para crear un proyecto.
 */

public record CrearProyectoRequest(String name, String description, UUID ownerId) {}