package com.achiridev.proyecto01;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaBancariaServiceTest {
    
    private CuentaBancariaService service;
    private long idOrigen;
    private long idDestino;

    @BeforeEach
    void inicializarDatos() {
        service = new CuentaBancariaService();
        idOrigen = service.save(new CuentaBancaria("Daniel", 200.50)).getId();
        idDestino = service.save(new CuentaBancaria("Pepito", 120.50)).getId();
    }

    @Test
    void testTransferenciaExitosa() {
        service.transferir(idOrigen, idDestino, 100.50);
        double nuevoMontoOrigen = service.getById(idOrigen).getSaldo();
        double nuevoMontoDestino = service.getById(idDestino).getSaldo();
        assertEquals(100.00, nuevoMontoOrigen, 0.001);
        assertEquals(221.00, nuevoMontoDestino, 0.001);
    }
    @Test
    void testSaldoInsuficiente() {
        assertThrows(
            SaldoInsuficienteException.class,
            () -> service.transferir(idOrigen, idDestino, 300.02)
        );
    }
    @Test
    void testMontoNegativo() {
        assertThrows(
            RuntimeException.class,
            () -> service.transferir(idOrigen, idDestino, -100)
        );
    }

    @AfterEach
    void eliminarDatos() {
        service.deleteById(idOrigen);
        service.deleteById(idDestino);
    }
}
