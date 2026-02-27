package com.achiridev.proyecto04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.achiridev.proyecto01.SaldoInsuficienteException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private BancoClient bancoClient;
    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void testPagoExitoso() {
        Pago pagoFalso = new Pago("Daniel", 100.50);
        when(bancoClient.tieneSaldo("Daniel", 100.50))
                .thenReturn(true);
        pagoService.procesarPago("Daniel", 100.50);
        verify(pagoRepository, times(1)).guardar(pagoFalso);
    }

    @Test 
    void testSaldoInsuficiente() {
        Pago pagoFalso = new Pago("Daniel", 100.50);
        when(bancoClient.tieneSaldo("Daniel", 100.50))
                .thenReturn(false);
        SaldoInsuficienteException ex = assertThrows(
            SaldoInsuficienteException.class,
            () -> pagoService.procesarPago("Daniel", 100.50)
        );
        assertEquals("El saldo de esta cuenta no es suficiente", ex.getMessage());
        verify(pagoRepository, never()).guardar(pagoFalso);
    }

    @Test
    void testErrorDelBanco() {
        Pago pagoFalso = new Pago("Daniel", 100.50);
        when(bancoClient.tieneSaldo("Daniel", 100.50))
                .thenThrow(new RuntimeException("Ocurrio un error inesperado"));
        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> pagoService.procesarPago("Daniel", 100.50)
        );
        assertEquals("Ocurrio un error inesperado", ex.getMessage());
        verify(pagoRepository, never()).guardar(pagoFalso);
    }
}
