package org.taskifyapp.service;

import org.taskifyapp.model.dto.response.UserResponse;
import org.taskifyapp.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

    UserResponse getUserResponseById(Long id);

    UserResponse getUserResponseByUsername(String username);

    UserResponse getUserResponseByEmail(String email);

    List<UserResponse> getUserResponseByOrganizationId(Long organizationId);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    void saveUser(User user);

}
