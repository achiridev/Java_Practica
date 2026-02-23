package com.achiridev.proyecto02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class UserValidatorTest {

    private UserValidator userValidator;

    @BeforeEach
    void inicializarValidator() {
        userValidator = new UserValidator();
    }

    @Test
    void testUserIngresadoNulo() {
        assertThrows(
            RuntimeException.class,
            () -> userValidator.validar(null)
        );
    }
    @Test
    void testUsernameNulo() {
        Usuario user = new Usuario(null, "dachiri@unsa.edu.pe", 18);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> userValidator.validar(user)
        );
        assertEquals("El username no puede ser nulo", ex.getMessage());
    }
    @Test
    void testUsernameLongitudInorrecta() {
        Usuario user = new Usuario("Dan", "dachiri@unsa.edu.pe", 18);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> userValidator.validar(user)
        );
        assertEquals("El nombre debe ser mas largo de 4 caracteres", ex.getMessage());
    }
    @Test
    void testEmailNoContieneArroba() {
        Usuario user = new Usuario("Daniel", "dachiriAunsa.edu.pe", 18);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> userValidator.validar(user)
        );
        assertEquals("El email debe tener un \"@\"", ex.getMessage());
    }
    @Test
    void testMenorDeEdad() {
        Usuario user = new Usuario("Daniel", "dachiri@unsa.edu.pe", 17);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> userValidator.validar(user)
        );
        assertEquals("Edad minima es 18", ex.getMessage());
    }
}
