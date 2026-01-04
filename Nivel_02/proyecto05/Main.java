package proyecto05;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tarjeta miTarjeta = new Tarjeta();
        Transferencia miTransferencia = new Transferencia();
        Criptomoneda miCriptomoneda = new Criptomoneda();
        ArrayList<Pago> listaPagos = new ArrayList<>();
        listaPagos.add(miTarjeta);
        listaPagos.add(miTransferencia);
        listaPagos.add(miCriptomoneda);
        int numeroAleatorio = (int)(Math.random()*3);
        // Procesamiento
        if (listaPagos.get(numeroAleatorio) instanceof Tarjeta t) {
            t.procesarPago();
        }
        else if (listaPagos.get(numeroAleatorio) instanceof Transferencia t) {
            t.procesarTransferencia();
        }
        else if (listaPagos.get(numeroAleatorio) instanceof Criptomoneda c) {
            c.procesarPago();
        }
        else {
            System.out.println("Referencia genetica de \"Pago\" no reconocida");
        }
    }
}
