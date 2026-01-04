package proyecto05;

public class Tarjeta extends Pago{
    @Override
    public void procesarPago() {
        System.out.println("Procesando pago con tarjeta.");
    }
}
