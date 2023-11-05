package org.taskifyapp.service;

import org.taskifyapp.model.dto.response.UserResponse;
import org.taskifyapp.model.entity.User;

import java.util.List;

public interface UserService {

    UserResponse getUserResponseById(Long id);

    UserResponse getUserResponseByUsername(String username);

    UserResponse getUserResponseByEmail(String email);

    List<UserResponse> getUserResponseByOrganizationId(Long organizationId);

    void saveUser(User user);

}
