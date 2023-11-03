package org.taskifyapp.model.dto.request;


import lombok.Data;

import jakarta.validation.constraints.Size;

@Data
public class OrganizationRegistrationRequest {

    @Size(min = 1, max = 255)
    private String username;

    @Size(min = 1, max = 255)
    private String organizationName;

    @Size(min = 1, max = 20)
    private String phoneNumber;

    @Size(min = 1, max = 255)
    private String address;

}
