package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;
import org.taskifyapp.service.ManagementService;


@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {
    @Override
    public void registerOrganization(OrganizationRegistrationRequest request) {

    }
}
