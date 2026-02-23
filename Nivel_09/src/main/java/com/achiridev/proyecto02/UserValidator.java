package com.achiridev.proyecto02;

public class UserValidator {
    public void validar(Usuario user) {
        if (user == null)
            throw new RuntimeException("Usuario a validar nulo");

        if (user.getUsername() == null)
            throw new IllegalArgumentException("El username no puede ser nulo");
        if (user.getUsername().length() < 4)
            throw new IllegalArgumentException("El nombre debe ser mas largo de 4 caracteres");
        if (!user.getEmail().contains("@"))
            throw new IllegalArgumentException("El email debe tener un \"@\"");
        if (user.getEdad() <= 18)
            throw new IllegalArgumentException("Edad minima es 18");
    }
}
