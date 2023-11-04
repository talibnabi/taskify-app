package org.taskifyapp.exception;

public class PasswordNotMatchedException extends RuntimeException {
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
