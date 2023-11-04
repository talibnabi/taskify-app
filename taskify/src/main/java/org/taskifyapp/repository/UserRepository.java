package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskifyapp.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findByEmail(String email);
}
