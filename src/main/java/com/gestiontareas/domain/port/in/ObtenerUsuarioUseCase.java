package com.gestiontareas.domain.port.in;

import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;

/**
 * Puerto de entrada para obtener un usuario por su ID.
 */

public interface ObtenerUsuarioUseCase {

    User ejecutar(UserId id);
}