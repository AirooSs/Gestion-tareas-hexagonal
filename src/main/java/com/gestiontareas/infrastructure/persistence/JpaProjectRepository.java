package com.gestiontareas.infrastructure.persistence;

import com.gestiontareas.domain.model.project.Project;
import com.gestiontareas.domain.model.project.ProjectId;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.out.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de salida — implementa el puerto ProjectRepository del dominio.
 */

@Component
public class JpaProjectRepository implements ProjectRepository {

    private final SpringDataProjectRepository springDataProjectRepository;

    public JpaProjectRepository(SpringDataProjectRepository springDataProjectRepository) {
        this.springDataProjectRepository = springDataProjectRepository;
    }

    @Override
    public Project save(Project project) {
        ProjectEntity entity = ProjectMapper.toEntity(project);
        ProjectEntity saved = springDataProjectRepository.save(entity);
        return ProjectMapper.toDomain(saved);
    }

    @Override
    public Optional<Project> findById(ProjectId id) {
        return springDataProjectRepository.findById(id.value())
            .map(ProjectMapper::toDomain);
    }

    @Override
    public List<Project> findByOwnerId(UserId ownerId) {
        return springDataProjectRepository.findByOwnerId(ownerId.value())
            .stream()
            .map(ProjectMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(ProjectId id) {
        springDataProjectRepository.deleteById(id.value());
    }
}