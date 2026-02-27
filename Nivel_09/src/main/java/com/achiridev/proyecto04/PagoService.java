package com.achiridev.proyecto04;

import com.achiridev.proyecto01.SaldoInsuficienteException;

public class PagoService {
    private BancoClient bancoClient;
    private PagoRepository pagoRepository;

    public PagoService(BancoClient bancoClient, PagoRepository pagoRepository) {
        this.bancoClient = bancoClient;
        this.pagoRepository = pagoRepository;
    }

    public void procesarPago(String cuenta, double monto) {
        if (!bancoClient.tieneSaldo(cuenta, monto)) 
                throw new SaldoInsuficienteException("El saldo de esta cuenta no es suficiente");
        Pago pago = new Pago(cuenta, monto);
        pagoRepository.guardar(pago);
    }
}
