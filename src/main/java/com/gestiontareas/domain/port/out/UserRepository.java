package com.gestiontareas.domain.port.out;

import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.model.user.Email;

import java.util.Optional;

/**
 * Puerto de salida para la persistencia de usuarios.
 */

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UserId id);

    /** Busca un usuario por email,  para login y validar duplicados */
    Optional<User> findByEmail(Email email);

    void deleteById(UserId id);
}