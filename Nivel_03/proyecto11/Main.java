package proyecto11;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto 11 - Agenda telefónica ordenada");
        AgendaContactos miAgenda = new AgendaContactos();
        miAgenda.agregarContacto("Pepito", "987654321");
        miAgenda.agregarContacto("Alonso", "926257434");
        miAgenda.agregarContacto("Yanet", "+51 935234634");
        miAgenda.agregarContacto("Achiri", "913105709");
        miAgenda.agregarContacto("Pepito", "912345678");

        System.out.println("Numero de Pepito: "+miAgenda.buscarTelefono("Pepito"));
        miAgenda.mostrarAgenda();
    }
}
