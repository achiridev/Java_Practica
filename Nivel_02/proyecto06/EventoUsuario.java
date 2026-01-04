package proyecto06;

public class EventoUsuario extends Evento{
    @Override
    public void procesar() {
        System.out.println("Evento de usuario procesado");
    }
}
