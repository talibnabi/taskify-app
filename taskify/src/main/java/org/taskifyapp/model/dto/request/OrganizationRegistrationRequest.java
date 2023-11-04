package org.taskifyapp.model.dto.request;


import lombok.Data;


@Data
public class OrganizationRegistrationRequest {

    private String username;

    private String organizationName;

    private String phoneNumber;

    private String address;

}
