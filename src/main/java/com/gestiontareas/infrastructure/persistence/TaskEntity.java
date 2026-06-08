package com.gestiontareas.infrastructure.persistence;

import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad JPA que representa la tabla "tareas" en PostgreSQL.
 * 
 * Esta clase existe SOLO en infraestructura — el dominio nunca la ve. Usamos
 * Lombok aquí porque no es dominio puro, es una clase técnica.
 * 
 * Cuando no pongo nada esque nullable = true
 */

@Entity
@Table(name = "tareas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskEntity {

	@Id
	@Column(columnDefinition = "uuid")
	private UUID id;

	@Column(nullable = false)
	private String titulo;

	private String descripcion;

	@Column(nullable = false)
	private String status;

	@Column(name = "project_id", nullable = false, columnDefinition = "uuid")
	private UUID projectId;

	@Column(name = "assigned_to", columnDefinition = "uuid")
	private UUID assignedTo;

}
