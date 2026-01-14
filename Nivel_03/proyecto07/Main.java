package proyecto07;

import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto 07 - Gestión de Correos Electrónicos");
        Set<Correo> correoSet = new HashSet<>();
        correoSet.add(new Correo("Buenos dias Benito", "Daniel"));
        correoSet.add(new Correo("Paga tu cuota", "PepitoCraft"));
        correoSet.add(new Correo("Oferta especial", "TiendaXYZ"));
        correoSet.add(new Correo("Buenos dias Benito", "OtroProveedor")); // Duplicado

        mostrarCorreos(correoSet);
        System.out.println("Cantidad total de correos: " + correoSet.size());
    }
    public static void mostrarCorreos(Set<Correo> correos) {
        for (Correo correo : correos) {
            System.out.println(correo);
        }
    }
}
