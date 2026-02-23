package com.achiridev.proyecto01;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
    }

    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
