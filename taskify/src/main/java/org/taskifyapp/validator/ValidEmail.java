package org.taskifyapp.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import org.taskifyapp.validator.pattern.RegexPatterns;
import org.taskifyapp.util.ErrorMessages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

@Pattern(regexp = RegexPatterns.EMAIL, message = ErrorMessages.EMAIL_IS_NOT_VALID)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidEmail {

    String message() default ErrorMessages.EMAIL_IS_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

