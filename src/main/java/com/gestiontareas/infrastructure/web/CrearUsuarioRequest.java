package com.gestiontareas.infrastructure.web;

/**
 * DTO de entrada para crear un usuario.
 */

public record CrearUsuarioRequest(String name, String email) {}