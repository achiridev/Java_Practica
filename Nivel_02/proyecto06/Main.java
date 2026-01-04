package proyecto06;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Evento> listaEventos = new ArrayList<>();
        listaEventos.add(new EventoError());
        listaEventos.add(new EventoSistema());
        listaEventos.add(new EventoUsuario());
        for (Evento e : listaEventos) {
            if (e instanceof EventoError ee) {
                ee.procesarError();
                continue;
            }
            e.procesar();
        }
    }
}
