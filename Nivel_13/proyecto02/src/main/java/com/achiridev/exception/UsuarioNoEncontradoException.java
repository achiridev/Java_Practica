package com.achiridev.exception;

public class UsuarioNoEncontradoException extends RuntimeException{

    public UsuarioNoEncontradoException() {
    }

    public UsuarioNoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
