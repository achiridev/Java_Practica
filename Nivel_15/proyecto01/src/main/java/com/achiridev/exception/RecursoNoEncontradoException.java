package com.achiridev.exception;

public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException() {
    }

    public RecursoNoEncontradoException(String message) {
        super(message);
    }

    public RecursoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
