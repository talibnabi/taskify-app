package org.taskifyapp.model.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.taskifyapp.validator.ValidEmail;
import org.taskifyapp.validator.ValidPassword;

import jakarta.validation.constraints.Size;
import java.util.Objects;

@Data
public class AdminRegistrationRequest {

    @Size(min = 1, max = 255)
    private String username;

    @ValidEmail
    @Size(min = 1, max = 255)
    private String email;

    @ValidPassword
    private String password;

    @ValidPassword
    private String passwordConfirmation;

    @JsonIgnore
    public boolean isPasswordsMatched() {
        return Objects.nonNull(password) && password.equals(passwordConfirmation);
    }

}
