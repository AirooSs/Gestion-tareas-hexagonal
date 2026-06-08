package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.task.TaskStatus;
import com.gestiontareas.domain.port.out.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test unitario de ActualizarEstadoTareaService.
 * No levanta Spring ni conecta a la base de datos.
 * Mockito simula el repositorio.
 */

@ExtendWith(MockitoExtension.class)
class ActualizarEstadoTareaServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private ActualizarEstadoTareaService actualizarEstadoTareaService;

    @BeforeEach
    void setUp() {
        actualizarEstadoTareaService =
                new ActualizarEstadoTareaService(taskRepository);
    }

    @Test
    void deberiaActualizarElEstadoDeLaTarea() {

        // Arrange
        ProjectId projectId = ProjectId.of(UUID.randomUUID());

        Task tarea = new Task(
                TaskId.generate(),
                "Tarea de prueba",
                "Descripción de prueba",
                projectId
        );

        when(taskRepository.findById(tarea.getId()))
                .thenReturn(Optional.of(tarea));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task resultado = actualizarEstadoTareaService.ejecutar(
                tarea.getId(),
                TaskStatus.HECHO
        );

        // Assert
        assertNotNull(resultado);
        assertEquals(TaskStatus.HECHO, resultado.getStatus());
        assertEquals(tarea.getId(), resultado.getId());
        assertEquals("Tarea de prueba", resultado.getTitle());
    }

    @Test
    void deberiaLanzarExcepcionSiLaTareaNoExiste() {

        // Arrange
        TaskId id = TaskId.generate();

        when(taskRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> actualizarEstadoTareaService.ejecutar(
                        id,
                        TaskStatus.HECHO
                )
        );

        assertTrue(
                exception.getMessage()
                        .contains("Tarea no encontrada")
        );
    }
}