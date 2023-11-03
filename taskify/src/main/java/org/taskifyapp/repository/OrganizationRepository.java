package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskifyapp.model.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
