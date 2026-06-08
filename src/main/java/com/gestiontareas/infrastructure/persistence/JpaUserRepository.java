package com.gestiontareas.infrastructure.persistence;

import com.gestiontareas.domain.model.user.Email;
import com.gestiontareas.domain.model.user.User;
import com.gestiontareas.domain.model.user.UserId;
import com.gestiontareas.domain.port.out.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador de salida — implementa el puerto UserRepository del dominio.
 */

@Component
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = springDataUserRepository.save(entity);
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return springDataUserRepository.findById(id.value())
            .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return springDataUserRepository.findByEmail(email.value())
            .map(UserMapper::toDomain);
    }

    @Override
    public void deleteById(UserId id) {
        springDataUserRepository.deleteById(id.value());
    }
}