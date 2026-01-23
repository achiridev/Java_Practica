package proyecto04;

public class CuentaService {
    public void procesarPago(double monto) {
        try {
            UtilPago.validarMonto(monto);
            UtilPago.conectarPasarelaPago();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Simulacion de volver a pedir monto");
        }
        catch (PasarelaPagoException e) {
            System.out.println(e.getMessage());
        }
    }
}
