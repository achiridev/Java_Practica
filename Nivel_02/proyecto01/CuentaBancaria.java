package proyecto01;

public class CuentaBancaria {
    public final Integer numeroDeCuenta;
    private int saldo;
    String tipoDeCuenta;

    public CuentaBancaria(int numeroDeCuenta, int saldoInicial, String tipoDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldoInicial;
        this.tipoDeCuenta = tipoDeCuenta;
    }
    
    public int getSaldo() {
        return this.saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
