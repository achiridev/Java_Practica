package proyecto10;

import java.util.stream.*;
import java.util.function.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 9 — Procesamiento de nombres sin modificar la lista original");

        Random random = new Random();
        Consumer<String> imprimirLI = (texto) -> System.out.println("- "+texto);

        System.out.println("\nLista usuarios");
        Stream.of("danielUser", "luisUser", "mariaUser", "marceloUser", "pepitoUser")
                .forEach(imprimirLI::accept);
        
        System.out.println("\nLista codigos de regalo");
        Stream.of(1001,2335,9832,5034)
                .forEach((codigo) -> {
                    boolean canjeado = random.nextBoolean();
                    imprimirLI.accept(codigo+" Canjeado: "+canjeado);
                });
        try {
            //Stream.of(null)
            //        .forEach(System.out::println);
        }
        catch (NullPointerException e){
            System.err.println("\n"+e.getMessage());
            Stream.ofNullable(null)
                    .forEach(System.out::println);
        }
    }
}
