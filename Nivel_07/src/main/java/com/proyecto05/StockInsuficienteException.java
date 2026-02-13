package com.proyecto05;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException() {
    }

    public StockInsuficienteException(String message) {
        super(message);
    }

    public StockInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
