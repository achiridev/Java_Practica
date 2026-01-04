package proyecto06;

public class EventoSistema extends Evento{
    @Override
    public void procesar() {
        System.out.println("Evento del sistema procesado");
    }
}
