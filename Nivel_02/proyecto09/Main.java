package proyecto09;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Usuario> listaUsuarios = new HashSet<>();
        listaUsuarios.add(new Usuario("danielUser", "danielPass", "cliente"));
        listaUsuarios.add(new Usuario("mariaUser", "mariaPass", "cliente"));
        listaUsuarios.add(new Usuario("pepitoUser", "pepitoPass", "administrador"));
        listaUsuarios.add(new Usuario("juanUser", "juanPass", "empleado"));
        String eleccion = "s";
        ingresar:
        do {
            System.out.println("Ingrese el nombre de usuario: ");
            String username = sc.nextLine();
            for (Usuario u : listaUsuarios) {
                if (u.getUsername().equals(username)) {
                    System.out.println("Por favor ingresar un nombre de usuario que no exista.");
                    continue ingresar;
                }
            }
            System.out.println("Ingrese la contraseña: ");
            String password = sc.nextLine();
            System.out.println("Ingrese el rol que tendra este usuario.");
            String rol = sc.nextLine();
            listaUsuarios.add(new Usuario(username, password, rol));
            System.out.println("Desea ingresar otro dato? s/n");
            eleccion = sc.nextLine();
        }
        while (!eleccion.equalsIgnoreCase("n"));

        System.out.println(listaUsuarios);
        sc.close();
    }
}
