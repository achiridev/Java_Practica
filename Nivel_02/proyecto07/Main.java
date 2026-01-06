package proyecto07;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<Cliente> listaClientes = new HashSet<>();
        listaClientes.add(new Cliente(1234, "Daniel", "danielEmail@gmail.com"));
        listaClientes.add(new Cliente(7890, "Pepito", "pepitoEmail@gmail.com"));
        listaClientes.add(new Cliente(1234, "Cooper", "cooperEmail@gmail.com")); // dni repetido
        listaClientes.add(new Cliente(4567, "Alonso", "alonsoEmail@gmail.com"));
        
        System.out.println(listaClientes); // no aparece el del dni repetido
    }
}
