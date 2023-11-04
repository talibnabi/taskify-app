package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.dto.request.AuthenticationRequest;
import org.taskifyapp.model.dto.response.AuthAndRegisterResponse;

public interface AuthService {
    AuthAndRegisterResponse registerUser(RegistrationRequest request);

    AuthAndRegisterResponse authenticateUser(AuthenticationRequest request);
    void registerOrganization(OrganizationRegistrationRequest request);
}
