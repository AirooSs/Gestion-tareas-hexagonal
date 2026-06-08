package com.gestiontareas.infrastructure.web;

/**
 * DTO de entrada para actualizar el estado de una tarea.
 */

public record ActualizarEstadoRequest(String estado) {
}