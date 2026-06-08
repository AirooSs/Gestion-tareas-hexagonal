package com.gestiontareas.infrastructure.persistence;

import com.gestiontareas.domain.model.task.Task;
import com.gestiontareas.domain.model.task.TaskId;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.port.out.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de salida — implementa el puerto TaskRepository del dominio usando
 * Spring Data JPA.
 * 
 * El dominio solo conoce TaskRepository (la interfaz). Nunca sabe que existe
 * esta clase.
 */
@Component
public class JpaTaskRepository implements TaskRepository {

	private final SpringDataTaskRepository springDataTaskRepository;

	public JpaTaskRepository(SpringDataTaskRepository springDataTaskRepository) {
		this.springDataTaskRepository = springDataTaskRepository;
	}

	/** Guarda una tarea nueva o actualiza una existente */
	@Override
	public Task save(Task task) {
		TaskEntity entity = TaskMapper.toEntity(task);
		TaskEntity saved = springDataTaskRepository.save(entity);
		return TaskMapper.toDomain(saved);
	}

	/** Busca una tarea por su ID */
	@Override
	public Optional<Task> findById(TaskId id) {
		return springDataTaskRepository.findById(id.value()).map(TaskMapper::toDomain);
	}

	/** Devuelve todas las tareas de un proyecto como una lista */
	@Override
	public List<Task> findByProjectId(ProjectId projectId) {
		return springDataTaskRepository.findByProjectId(projectId.value()).stream().map(TaskMapper::toDomain)
				.collect(Collectors.toList());
	}

	/** Elimina una tarea por su ID */
	@Override
	public void deleteById(TaskId id) {
		springDataTaskRepository.deleteById(id.value());
	}
}