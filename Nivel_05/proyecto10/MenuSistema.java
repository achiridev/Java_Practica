package proyecto10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSistema {
    private Scanner sc;

    public MenuSistema() {
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        try {
            System.out.println("Ingrese una de las siguientes opciones.");
            System.out.println("1 - Ingresar Log");
            System.out.println("0 - Salir");
            int ingresado = sc.nextInt();
            sc.nextLine();
            switch (ingresado) {
                case 1:
                    ingresarLog();
                    break;
                case 0:
                    despedida();
                    return;
                default:
                    throw new InputMismatchException();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Valor ingresado no valido, porfavor volver a ingresar.\n");
            mostrarMenu();
        }
    }
    public void ingresarLog() {
        System.out.println("\nIngresar mensaje del nuevo log: ");
        String mensaje = sc.nextLine();
        System.out.println();
        Services.getLogRotationService().escribirLog(mensaje);
        System.out.println("Se ingreso el log exitosamente.");
        System.out.println("Presione \"enter\" para continuar...");
        sc.nextLine();
        mostrarMenu();
    }
    public void despedida() {
        System.out.println("Gracias por usar este sistema.");
    }
}
