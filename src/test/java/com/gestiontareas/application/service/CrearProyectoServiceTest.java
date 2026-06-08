package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.out.ProjectRepository;
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
 * Test unitario de CrearProyectoService.
 * No levanta Spring ni conecta a la base de datos.
 * Mockito simula el repositorio.
 */

@ExtendWith(MockitoExtension.class)
class CrearProyectoServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    private CrearProyectoService crearProyectoService;

    @BeforeEach
    void setUp() {
        crearProyectoService = new CrearProyectoService(projectRepository);
    }

    @Test
    void deberiaCrearUnProyectoCorrectamente() {

        // Arrange
        String nombre = "Proyecto de prueba";
        String descripcion = "Descripción del proyecto";
        UserId ownerId = UserId.of(UUID.randomUUID());

        when(projectRepository.save(any(Project.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        // Act
        Project resultado = crearProyectoService.ejecutar(
                nombre,
                descripcion,
                ownerId
        );

        // Assert
        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals(nombre, resultado.getName());
        assertEquals(descripcion, resultado.getDescription());
        assertEquals(ownerId, resultado.getOwnerId());
    }

    @Test
    void deberiaGenerarUnIdUnicoParaCadaProyecto() {

        // Arrange
        UserId ownerId = UserId.of(UUID.randomUUID());

        when(projectRepository.save(any(Project.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        // Act
        Project proyecto1 = crearProyectoService.ejecutar(
                "Proyecto 1",
                "Descripción 1",
                ownerId
        );

        Project proyecto2 = crearProyectoService.ejecutar(
                "Proyecto 2",
                "Descripción 2",
                ownerId
        );

        // Assert
        assertNotEquals(
                proyecto1.getId(),
                proyecto2.getId()
        );
    }
}