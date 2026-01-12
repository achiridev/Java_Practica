package proyecto02;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        inicializarDatos(listaClientes, listaEmpleados);

        ProcesadorListas.imprimirNombres(listaClientes);
        ProcesadorListas.imprimirNombres(listaEmpleados);

        System.out.println("#### Agregando Empleado ####");
        ProcesadorListas.agregarEmpledo(listaEmpleados);
        ProcesadorListas.imprimirNombres(listaEmpleados);
    }
    public static void inicializarDatos(ArrayList<Cliente> listaClientes, ArrayList<Empleado> listaEmpleados) {
        listaClientes.add(new Cliente("Daniel", "Achiri Cuevas", 17, true, "dachiri@unsa.edu.pe"));
        listaClientes.add(new Cliente("Leo", "Cavero Ale", 19, true, "lcavero@unda.edu.pe"));
        listaClientes.add(new Cliente("Yohao", "Apaza Torres", 18, false, "soyYohao@gmail.com"));
        listaEmpleados.add(new Empleado("Pepito", "Alcantara", 20, "E001", "basico"));
        listaEmpleados.add(new Empleado("Andrea", "Torres", 34, "E002", "gerente"));
        listaEmpleados.add(new Empleado("Luis", "Pataluis", 50, "E003", "basico"));
    }
}
