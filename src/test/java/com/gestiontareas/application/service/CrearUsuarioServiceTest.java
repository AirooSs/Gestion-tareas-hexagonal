package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.port.out.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearUsuarioServiceTest {

	@Mock
	private UserRepository userRepository;

	private CrearUsuarioService crearUsuarioService;

	@BeforeEach
	void setUp() {
		crearUsuarioService = new CrearUsuarioService(userRepository);
	}

	@Test
	void deberiaCrearUnUsuarioCorrectamente() {

		// Arrange
		String nombre = "Francisco";
		Email email = new Email("fran@gmail.com");

		when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

		// Act
		User resultado = crearUsuarioService.ejecutar(nombre, email);

		// Assert
		assertNotNull(resultado);
		assertNotNull(resultado.getId());
		assertEquals(nombre, resultado.getName());
		assertEquals(email, resultado.getEmail());
	}

	@Test
	void deberiaGenerarIdsDistintosParaCadaUsuario() {

		// Arrange
		when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

		// Act
		User usuario1 = crearUsuarioService.ejecutar("Francisco", new Email("fran1@gmail.com"));

		User usuario2 = crearUsuarioService.ejecutar("Pedro", new Email("pedro@gmail.com"));

		// Assert
		assertNotEquals(usuario1.getId(), usuario2.getId());
	}

	@Test
	void deberiaGuardarUsuarioEnRepositorio() {

		String nombre = "Francisco";
		Email email = new Email("fran@gmail.com");

		when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

		crearUsuarioService.ejecutar(nombre, email);

		verify(userRepository).save(any(User.class));
	}
}