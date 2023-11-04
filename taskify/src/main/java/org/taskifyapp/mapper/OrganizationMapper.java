package org.taskifyapp.mapper;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;
import org.taskifyapp.model.entity.Organization;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {
    private final ModelMapper modelMapper;

    public Organization registrationRequestToUserMapper(OrganizationRegistrationRequest request) {
        return modelMapper.map(request, Organization.class);
    }
}
