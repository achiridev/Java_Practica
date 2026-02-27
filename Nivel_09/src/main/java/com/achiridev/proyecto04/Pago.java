package com.achiridev.proyecto04;

import java.util.Objects;

public class Pago {
    private String cuenta;
    private double monto;

    public Pago() {}
    public Pago(String cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pago { Cuenta: ").append(cuenta)
                .append(", Monto: ").append(monto)
                .append(" }");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return cuenta != null && cuenta.equals(pago.getCuenta());
    }
    @Override
    public int hashCode() {
        return Objects.hash(cuenta);
    }
}
