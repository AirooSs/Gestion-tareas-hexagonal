package com.gestiontareas.domain.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario del Value Object Email.
 * Verifica que las reglas de validación del dominio funcionan correctamente.
 */

class EmailTest {

    @Test
    void deberiaCrearEmailValido() {
        // Act
        Email email = new Email("fran@gestiontareas.com");

        // Assert
        assertEquals("fran@gestiontareas.com", email.value());
    }

    @Test
    void deberiaLanzarExcepcionCuandoEmailEsNulo() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new Email(null)
        );
        assertTrue(ex.getMessage().contains("vacío"));
    }

    @Test
    void deberiaLanzarExcepcionCuandoEmailEstaVacio() {
        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new Email("")
        );
    }

    @Test
    void deberiaLanzarExcepcionCuandoEmailNoTieneArroba() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("emailsinformato")
        );
        assertTrue(ex.getMessage().contains("formato"));
    }
}