package com.gestiontareas.application.service;

import com.gestiontareas.domain.exception.RecursoNoEncontradoException;
import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.ObtenerUsuarioUseCase;
import com.gestiontareas.domain.port.out.UserRepository;

/**
 * Implementación del caso de uso ObtenerUsuarioUseCase.
 */

public class ObtenerUsuarioService implements ObtenerUsuarioUseCase {

    private final UserRepository userRepository;

    public ObtenerUsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User ejecutar(UserId id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con id: " + id.value()));
    }
}