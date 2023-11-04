package org.taskifyapp.service;

import org.taskifyapp.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);



}
