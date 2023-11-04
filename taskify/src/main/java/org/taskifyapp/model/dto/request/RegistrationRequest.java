package org.taskifyapp.model.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.taskifyapp.validator.constraints.ValidEmail;
import org.taskifyapp.validator.constraints.ValidPassword;

import java.util.Objects;

@Data
public class RegistrationRequest {

    private String username;

    @ValidEmail
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
