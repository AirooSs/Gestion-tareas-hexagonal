package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.User;

/**
 * Puerto de entrada para crear un usuario.
 */

public interface CrearUsuarioUseCase {

    User ejecutar(String name, Email email);
}