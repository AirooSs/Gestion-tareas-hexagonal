package com.gestiontareas.domain.model.task;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario de la entidad Task.
 * Verifica que las reglas de negocio del dominio funcionan correctamente.
 */

class TaskTest {

    private Task tarea;

    @BeforeEach
    void setUp() {
        tarea = new Task(
            TaskId.generate(),
            "Tarea de prueba",
            "Descripción",
            ProjectId.of(UUID.randomUUID())
        );
    }

    @Test
    void deberiaCrearTareaConEstadoPendienteporDefecto() {
        assertEquals(TaskStatus.PENDIENTE, tarea.getStatus());
    }

    @Test
    void deberiaCambiarEstadoCorrectamente() {
        tarea.cambiarEstado(TaskStatus.EN_PROGRESO);
        assertEquals(TaskStatus.EN_PROGRESO, tarea.getStatus());
    }

    @Test
    void deberiaAsignarUsuarioCorrectamente() {
        UserId usuarioId = UserId.of(UUID.randomUUID());
        tarea.asignarA(usuarioId);
        assertEquals(usuarioId, tarea.getAssignedTo());
    }

    @Test
    void deberiaTenerAssignedToNuloAlCrearse() {
        assertNull(tarea.getAssignedTo());
    }
}