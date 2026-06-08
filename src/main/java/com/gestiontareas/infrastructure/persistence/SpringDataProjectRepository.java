package com.gestiontareas.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Interfaz Spring Data JPA para proyectos.
 */

public interface SpringDataProjectRepository extends JpaRepository<ProjectEntity, UUID> {

    List<ProjectEntity> findByOwnerId(UUID ownerId);
}