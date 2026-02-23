package com.achiridev.proyecto01;

public class CuentaBancaria {
    private long id;
    private String titular;
    private double saldo;

    public CuentaBancaria() {}
    public CuentaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }
    public CuentaBancaria(long id, String titular, double saldo) {
        this(titular, saldo);
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CuentaBancaria { ID: ").append(id)
                .append(", Titular: ").append(titular)
                .append(", Saldo: ").append(saldo).append(" }");
        return sb.toString();
    }
}
