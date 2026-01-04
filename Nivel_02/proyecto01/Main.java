package proyecto01;

public class Main {
    public static void main(String[] args) {
        CuentaBancaria miCuenta = new CuentaBancaria(1234, 0, "Debito");
        // Intento consultar-modificar numeroDeCuenta
        int miNumeroDeCuenta = miCuenta.numeroDeCuenta;
        System.out.println(miNumeroDeCuenta);

        // Intento consultar-modificar saldo
        int miSaldo = miCuenta.getSaldo();
        System.out.println(miSaldo);

        miCuenta.setSaldo(100);
        System.out.println("Saldo modificado exitosamente.");

        // Intento consultar-modificar tipoDeCuenta
        String miTipoDeCuenta = miCuenta.tipoDeCuenta;
        System.out.println("Mi tipo de cuenta es: "+miTipoDeCuenta);
        miCuenta.tipoDeCuenta = "Credito";
        System.out.print("Cuenta modificada exitosamente: "+miCuenta.tipoDeCuenta);
    }
}
