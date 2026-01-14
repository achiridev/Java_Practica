package proyecto11;

import java.util.Map;
import java.util.TreeMap;

public class AgendaContactos {
    private final Map<String, String> agenda;

    public AgendaContactos() {
        this.agenda = new TreeMap<>();
    }
    public void agregarContacto(String nombre, String telefono) {
        agenda.put(nombre, telefono);
    }
    public String buscarTelefono(String nombre) {
        if (agenda.containsKey(nombre)) return agenda.get(nombre);
        return "Desconocido";
    }
    public void mostrarAgenda() {
        System.out.println("Agenda de contactos");
        for (Map.Entry<String, String> entry : agenda.entrySet()) {
            System.out.println("- Contacto: "+entry.getKey()+", Telefono: "+entry.getValue());
        }
    }
}
