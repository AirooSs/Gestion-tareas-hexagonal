package com.gestiontareas.application.service;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.in.CrearUsuarioUseCase;
import com.gestiontareas.domain.port.out.UserRepository;

/**
 * Implementación del caso de uso CrearUsuarioUseCase.
 */

public class CrearUsuarioService implements CrearUsuarioUseCase {

    private final UserRepository userRepository;

    public CrearUsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User ejecutar(String name, Email email) {
        User usuario = new User(
            UserId.generate(),
            name,
            email
        );
        return userRepository.save(usuario);
    }
}