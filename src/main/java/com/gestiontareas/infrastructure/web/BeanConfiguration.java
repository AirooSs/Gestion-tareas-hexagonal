package com.gestiontareas.infrastructure.web;

import com.gestiontareas.application.service.ActualizarEstadoTareaService;
import com.gestiontareas.application.service.CrearProyectoService;
import com.gestiontareas.application.service.CrearTareaService;
import com.gestiontareas.application.service.CrearUsuarioService;
import com.gestiontareas.application.service.ListarProyectosPorUsuarioService;
import com.gestiontareas.application.service.ListarTareasPorProyectoService;
import com.gestiontareas.application.service.ObtenerTareaService;
import com.gestiontareas.application.service.ObtenerUsuarioService;
import com.gestiontareas.domain.port.in.ActualizarEstadoTareaUseCase;
import com.gestiontareas.domain.port.in.CrearProyectoUseCase;
import com.gestiontareas.domain.port.in.CrearTareaUseCase;
import com.gestiontareas.domain.port.in.CrearUsuarioUseCase;
import com.gestiontareas.domain.port.in.ListarProyectosPorUsuarioUseCase;
import com.gestiontareas.domain.port.in.ListarTareasPorProyectoUseCase;
import com.gestiontareas.domain.port.in.ObtenerTareaUseCase;
import com.gestiontareas.domain.port.in.ObtenerUsuarioUseCase;
import com.gestiontareas.domain.port.out.ProjectRepository;
import com.gestiontareas.domain.port.out.TaskRepository;
import com.gestiontareas.domain.port.out.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Spring — conecta los casos de uso con sus implementaciones.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public CrearTareaUseCase crearTareaUseCase(TaskRepository taskRepository) {
        return new CrearTareaService(taskRepository);
    }

    @Bean
    public ObtenerTareaUseCase obtenerTareaUseCase(TaskRepository taskRepository) {
        return new ObtenerTareaService(taskRepository);
    }

    @Bean
    public ActualizarEstadoTareaUseCase actualizarEstadoTareaUseCase(TaskRepository taskRepository) {
        return new ActualizarEstadoTareaService(taskRepository);
    }

    @Bean
    public ListarTareasPorProyectoUseCase listarTareasPorProyectoUseCase(TaskRepository taskRepository) {
        return new ListarTareasPorProyectoService(taskRepository);
    }

    @Bean
    public CrearProyectoUseCase crearProyectoUseCase(ProjectRepository projectRepository) {
        return new CrearProyectoService(projectRepository);
    }

    @Bean
    public ListarProyectosPorUsuarioUseCase listarProyectosPorUsuarioUseCase(ProjectRepository projectRepository) {
        return new ListarProyectosPorUsuarioService(projectRepository);
    }

    @Bean
    public CrearUsuarioUseCase crearUsuarioUseCase(UserRepository userRepository) {
        return new CrearUsuarioService(userRepository);
    }

    @Bean
    public ObtenerUsuarioUseCase obtenerUsuarioUseCase(UserRepository userRepository) {
        return new ObtenerUsuarioService(userRepository);
    }
}