package com.achiridev.exception;

public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException() {
    }

    public EmailDuplicadoException(String message) {
        super(message);
    }

    public EmailDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
