package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.taskifyapp.model.entity.Organization;
import org.taskifyapp.repository.OrganizationRepository;
import org.taskifyapp.service.OrganizationService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public void saveOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);

    }

    @Override
    public Optional<Organization> getOrganizationByName(String organizationName) {
        return organizationRepository.findOrganizationByOrganizationName(organizationName);
    }

    @Override
    public Optional<Organization> getOrganizationByAddress(String organizationAddress) {
        return organizationRepository.findOrganizationByAddress(organizationAddress);
    }
}
