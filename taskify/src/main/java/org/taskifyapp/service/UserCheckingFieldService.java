package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.RegistrationRequest;

public interface UserCheckingFieldService {
    void passwordMatchingChecking(RegistrationRequest registerRequest);

    void userUsernameDuplicatingChecking(RegistrationRequest registerRequest);

    void userEmailDuplicatingChecking(RegistrationRequest registerRequest);
}
