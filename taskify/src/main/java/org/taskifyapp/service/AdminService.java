package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.RegistrationRequest;

public interface AdminService {
    void createUser(RegistrationRequest registerRequest);
}
