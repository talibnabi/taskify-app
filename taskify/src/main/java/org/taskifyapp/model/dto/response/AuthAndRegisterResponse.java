package org.taskifyapp.model.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthAndRegisterResponse {
    private String token;
}
