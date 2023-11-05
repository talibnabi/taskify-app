package org.taskifyapp.model.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.taskifyapp.validator.constraints.validation.EmailValidation;
import org.taskifyapp.validator.constraints.validation.PasswordValidation;

import java.util.Objects;

@Data
public class RegistrationRequest {

    private String username;

    @EmailValidation
    private String email;

    @PasswordValidation
    private String password;

    @PasswordValidation
    private String passwordConfirmation;

    @JsonIgnore
    public boolean isPasswordsMatched() {
        return Objects.nonNull(password) && password.equals(passwordConfirmation);
    }

}
