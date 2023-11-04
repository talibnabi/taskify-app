package org.taskifyapp.service;

import org.taskifyapp.model.entity.Organization;

import java.util.Optional;


public interface OrganizationService {

    void saveOrganization(Organization organization);

    Optional<Organization> getOrganizationById(Long id);

    Optional<Organization> getOrganizationByName(String organizationName);

    Optional<Organization> getOrganizationByAddress(String organizationAddress);
}
