package com.gestiontareas.infrastructure.persistence;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;

/**
 * Convierte entre User (dominio) y UserEntity (infraestructura).
 */

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
            user.getId().value(),
            user.getName(),
            user.getEmail().value()
        );
    }

    public static User toDomain(UserEntity entity) {
        return new User(
            UserId.of(entity.getId()),
            entity.getName(),
            new Email(entity.getEmail())
        );
    }
}