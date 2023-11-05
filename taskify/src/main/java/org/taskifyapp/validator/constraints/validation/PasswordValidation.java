package org.taskifyapp.validator.constraints.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.taskifyapp.validator.constraints.PasswordConstraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.taskifyapp.util.ValidationConstants.PASSWORD_IS_NOT_VALID;

@Documented
@Constraint(validatedBy = PasswordConstraint.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PasswordValidation {

    String message() default PASSWORD_IS_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}