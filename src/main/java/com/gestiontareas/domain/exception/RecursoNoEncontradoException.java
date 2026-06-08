package com.gestiontareas.domain.exception;

/**
 * Excepción del dominio que se lanza cuando no se encuentra un recurso.
 */

public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}