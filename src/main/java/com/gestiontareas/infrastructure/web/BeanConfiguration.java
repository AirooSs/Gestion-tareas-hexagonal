package com.gestiontareas.infrastructure.web;

import com.gestiontareas.application.service.ActualizarEstadoTareaService;
import com.gestiontareas.application.service.CrearTareaService;
import com.gestiontareas.application.service.ListarTareasPorProyectoService;
import com.gestiontareas.application.service.ObtenerTareaService;
import com.gestiontareas.domain.port.in.ActualizarEstadoTareaUseCase;
import com.gestiontareas.domain.port.in.CrearTareaUseCase;
import com.gestiontareas.domain.port.in.ListarTareasPorProyectoUseCase;
import com.gestiontareas.domain.port.in.ObtenerTareaUseCase;
import com.gestiontareas.domain.port.out.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Spring — conecta los casos de uso con sus implementaciones.
 * 
 * Los servicios de aplicación no tienen @Service porque son dominio/aplicación
 * pura. Spring los registra aquí como beans sin que ellos sepan que Spring
 * existe.
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
}