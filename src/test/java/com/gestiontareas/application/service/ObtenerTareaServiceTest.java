package com.gestiontareas.application.service;

import com.gestiontareas.domain.exception.RecursoNoEncontradoException;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.port.out.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test unitario de ObtenerTareaService.
 */

@ExtendWith(MockitoExtension.class)
class ObtenerTareaServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private ObtenerTareaService obtenerTareaService;

    @BeforeEach
    void setUp() {
        obtenerTareaService = new ObtenerTareaService(taskRepository);
    }

    @Test
    void deberiaRetornarLaTareaCuandoExiste() {
        // Arrange
        TaskId taskId = TaskId.generate();
        ProjectId projectId = ProjectId.of(UUID.randomUUID());
        Task tarea = new Task(taskId, "Tarea test", "Descripción", projectId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(tarea));

        // Act
        Task resultado = obtenerTareaService.ejecutar(taskId);

        // Assert
        assertNotNull(resultado);
        assertEquals(taskId, resultado.getId());
        assertEquals("Tarea test", resultado.getTitle());
    }

    @Test
    void deberiaLanzarExcepcionCuandoLaTareaNoExiste() {
        // Arrange
        TaskId taskId = TaskId.generate();
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNoEncontradoException ex = assertThrows(
            RecursoNoEncontradoException.class,
            () -> obtenerTareaService.ejecutar(taskId)
        );

        assertTrue(ex.getMessage().contains("Tarea no encontrada"));
    }
}