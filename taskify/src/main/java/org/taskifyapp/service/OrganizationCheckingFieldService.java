package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;

public interface OrganizationCheckingFieldService {
    void organizationNameDuplicatingChecking(OrganizationRegistrationRequest registrationRequest);
}
