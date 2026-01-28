package proyecto09;

import java.util.List;
import java.util.Scanner;

public class InicioSesion {
    private Scanner sc;
    private boolean ejecutando;

    public InicioSesion() {
        sc = new Scanner(System.in);
        ejecutando = true;
    }

    public void iniciarSesion() {
        while (ejecutando) {
            try {
                List<String> nombresPerfiles = Central.getPerfiles().listarNombresPerfiles();
                mostrarMenu(nombresPerfiles);
                
                int eleccion = leerOpcion();
                procesarEleccion(eleccion, nombresPerfiles);
                
            } catch (Exception e) {
                System.out.println("Error: Opción no válida. Por favor, ingrese un número.");
                sc.nextLine(); // Limpiar buffer
            }
        }
        sc.close();
    }

    private void mostrarMenu(List<String> nombresPerfiles) {
        System.out.println("\n=== GESTOR DE PERFILES ===");
        System.out.println("Elija su cuenta:");
        
        for (int i = 0; i < nombresPerfiles.size(); i++) {
            System.out.println((i + 1) + " - " + nombresPerfiles.get(i));
        }
        
        System.out.println((nombresPerfiles.size() + 1) + " - Crear una nueva cuenta");
        System.out.println("0 - Salir");
        System.out.print("Opción: ");
    }

    private int leerOpcion() {
        int opcion = sc.nextInt();
        sc.nextLine(); // Consumir el salto de línea
        return opcion;
    }

    private void procesarEleccion(int eleccion, List<String> nombresPerfiles) {
        if (eleccion == 0) {
            despedida();
            ejecutando = false;
        } else if (eleccion == nombresPerfiles.size() + 1) {
            crearCuenta();
        } else if (eleccion > 0 && eleccion <= nombresPerfiles.size()) {
            menuPrincipal(nombresPerfiles.get(eleccion - 1));
        } else {
            System.out.println("Opción no válida. Por favor, elija una opción del menú.");
        }
    }

    public void menuPrincipal(String nombre) {
        System.out.println("\n=== Bienvenido " + nombre + " ===");
        String configuraciones = Central.getPerfiles().getConfiguraciones(nombre);
        
        if (!configuraciones.isEmpty()) {
            System.out.println("Tus configuraciones son:");
            System.out.println(configuraciones);
        } else {
            System.out.println("No se pudieron cargar las configuraciones.");
        }
        
        System.out.println("\nPresione Enter para volver al menú principal...");
        sc.nextLine();
    }

    public void crearCuenta() {
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        
        System.out.print("Ingrese la abreviación de su idioma (ej: es, en): ");
        String idioma = sc.nextLine().trim();
        
        if (Central.getPerfiles().agregarPerfil(nombre, idioma)) {
            System.out.println("✓ Perfil agregado exitosamente.");
        } else {
            System.out.println("✗ Error al crear el perfil. Puede que ya exista.");
        }
    }

    public void despedida() {
        System.out.println("\nGracias por usar esta aplicación. ¡Hasta pronto!");
    }
}