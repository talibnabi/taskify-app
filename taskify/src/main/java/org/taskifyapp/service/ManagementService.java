package org.taskifyapp.service;

import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;

public interface ManagementService {
    void registerOrganization(OrganizationRegistrationRequest request);

}
