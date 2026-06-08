package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskStatus;
import com.gestiontareas.domain.port.out.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test unitario de CrearTareaService.
 * No levanta Spring ni conecta a la base de datos.
 * Mockito simula el repositorio.
 */

@ExtendWith(MockitoExtension.class)
class CrearTareaServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private CrearTareaService crearTareaService;

    @BeforeEach
    void setUp() {
        crearTareaService = new CrearTareaService(taskRepository);
    }

    @Test
    void deberiaCrearUnaTareaConEstadoPendiente() {
        // Arrange
        String titulo = "Tarea de prueba";
        String descripcion = "Descripción de prueba";
        ProjectId projectId = ProjectId.of(UUID.randomUUID());

        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Task resultado = crearTareaService.ejecutar(titulo, descripcion, projectId);

        // Assert
        assertNotNull(resultado);
        assertEquals(titulo, resultado.getTitle());
        assertEquals(descripcion, resultado.getDescription());
        assertEquals(TaskStatus.PENDIENTE, resultado.getStatus());
        assertEquals(projectId, resultado.getProjectId());
    }

    @Test
    void deberiaGenerarUnIdUnicoParaCadaTarea() {
        // Arrange
        ProjectId projectId = ProjectId.of(UUID.randomUUID());
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Task tarea1 = crearTareaService.ejecutar("Tarea 1", "Desc 1", projectId);
        Task tarea2 = crearTareaService.ejecutar("Tarea 2", "Desc 2", projectId);

        // Assert
        assertNotEquals(tarea1.getId(), tarea2.getId());
    }
}