package com.gestiontareas.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Interfaz Spring Data JPA. Spring genera la implementación automáticamente en
 * tiempo de ejecución.
 */
public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, UUID> {

	List<TaskEntity> findByProjectId(UUID projectId);
}