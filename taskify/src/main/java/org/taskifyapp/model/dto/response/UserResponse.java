package org.taskifyapp.model.dto.response;


import lombok.Data;
import org.taskifyapp.model.enums.UserRole;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private UserRole userRole;
    private Long organizationId;
}
