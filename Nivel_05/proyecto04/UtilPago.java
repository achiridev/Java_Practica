package proyecto04;

public class UtilPago {
    public static void validarMonto(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto negativo");
        }
    }
    public static void conectarPasarelaPago() throws PasarelaPagoException {
        // Simulacion de fallo
        throw new PasarelaPagoException("Error en la Pasarela Pago");
    }
}
