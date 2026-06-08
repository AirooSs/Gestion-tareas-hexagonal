package com.gestiontareas.domain.model.user;

/**
 * Entidad principal del dominio User.
 * Representa un usuario del sistema.
 * 
 * Clase Java pura, sin Spring ni JPA.
 */

public class User {

    private final UserId id;
    private String name;
    private final Email email;

    public User(UserId id, String name, Email email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /** Actualiza el nombre del usuario */
    public void renombrar(String nuevoNombre) {
        this.name = nuevoNombre;
    }

    public UserId getId() { return id; }
    public String getName() { return name; }
    public Email getEmail() { return email; }
}