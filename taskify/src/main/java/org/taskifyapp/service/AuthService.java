package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.AdminRegistrationRequest;
import org.taskifyapp.model.dto.request.AuthenticationRequest;
import org.taskifyapp.model.dto.response.AuthAndRegisterResponse;

public interface AuthService {
    AuthAndRegisterResponse registerUser(AdminRegistrationRequest request);

    AuthAndRegisterResponse authenticateUser(AuthenticationRequest request);

}
