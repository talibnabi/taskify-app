package org.taskifyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.taskifyapp.model.entity.Organization;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findOrganizationById(@Param("id") Long id);

    Optional<Organization> findOrganizationByOrganizationName(@Param("name") String name);

    Optional<Organization> findOrganizationByAddress(@Param("name") String address);

}
