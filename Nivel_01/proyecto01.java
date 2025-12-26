import java.util.Scanner;

public class proyecto01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cadena (formato: valor1;valor2;...): ");
        String cadenaIngresada = sc.nextLine();
        if (cadenaIngresada == null) {
            System.out.println("Error: entrada nula");
            sc.close();
            return;
        }
        String[] elementosCadena = cadenaIngresada.split(";");
        int enteros = 0;
        int decimales = 0;
        int vacios = 0;
        for (String elemento : elementosCadena) {
            elemento = elemento.strip();
            if (elemento.isBlank() || elemento.equalsIgnoreCase("null")) {
                vacios++;
                continue;
            }
            Integer entero = intentarParsearEntero(elemento);
            if (entero != null) {
                enteros++;
                continue;
            }
            Double decimal = intentarParsearDecimal(elemento);
            if (decimal != null) {
                decimales++;
                continue;
            }
        }
        System.out.println("Enteros Válidos: "+enteros);
        System.out.println("Decimales Válidos: "+decimales);
        System.out.println("Entradas Vacias: "+vacios);
        sc.close();
    }
    public static Integer intentarParsearEntero(String elementoString) {
        try {
            return Integer.valueOf(elementoString);
        }
        catch (NumberFormatException e) {return null;}
    }
    public static Double intentarParsearDecimal(String elementoString) {
        try {
            return Double.valueOf(elementoString);
        }
        catch (NumberFormatException e) {return null;}
    }
}
