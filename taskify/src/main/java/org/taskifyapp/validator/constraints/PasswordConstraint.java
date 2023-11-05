package org.taskifyapp.validator.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.taskifyapp.validator.constraints.validation.PasswordValidation;

import java.util.Arrays;
import java.util.List;

public class PasswordConstraint implements ConstraintValidator<PasswordValidation, String> {

    @Override
    public void initialize(PasswordValidation arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(passwordValidation());
        RuleResult result = validator.validate(passwordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",", messages);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();
        return false;
    }

    private PasswordData passwordData(String password) {
        return new PasswordData(password);
    }

    private List<Rule> passwordValidation() {
        return Arrays.asList(lengthRule(), whitespaceRule());
    }

    private LengthRule lengthRule() {
        return new LengthRule(6, 25);
    }

    private WhitespaceRule whitespaceRule() {
        return new WhitespaceRule();
    }
}