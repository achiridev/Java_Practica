package com.proyecto06;

public class HabitacionNoDisponibleException extends RuntimeException {

    public HabitacionNoDisponibleException() {
    }

    public HabitacionNoDisponibleException(String message) {
        super(message);
    }

    public HabitacionNoDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
