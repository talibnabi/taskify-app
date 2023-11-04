package org.taskifyapp.exception.handler;


import com.fasterxml.jackson.core.JacksonException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ErrorMessage usernameNotFoundException(UsernameNotFoundException ex) {
        return new ErrorMessage(404, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası. Verilənlər bazası tərəfdə təyin edilmiş məhdudiyyətlər pozulub.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası. Verilənlər bazası tərəfdə təyin edilmiş məhdudiyyətlər pozulub.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<ErrorMessage> handleDataAccessException(DataAccessException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası. Verilənlər bazası tərəfdə təyin edilmiş məhdudiyyətlər pozulub.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler({JDBCException.class})
    public ResponseEntity<ErrorMessage> handleJDBCException(JDBCException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası. Verilənlər bazası tərəfdə təyin edilmiş məhdudiyyətlər pozulub.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(JacksonException.class)
    public ResponseEntity<ErrorMessage> handleJacksonException(JacksonException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler({HttpServerErrorException.class})
    public ResponseEntity<ErrorMessage> handleHttpServerErrorExceptionInternalServerError(HttpServerErrorException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.internalServerError().body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.info("Exception occurred:", ex);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Naməlum xəta");
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.info("Exception occurred:", ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), fieldErrors.get(0).getDefaultMessage());
        return ResponseEntity.unprocessableEntity().body(errorMessage);
    }
}