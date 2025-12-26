import java.util.Scanner;

public class proyecto01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cadena: ");
        String cadenaIngresada = sc.nextLine().replace("null", "");
        String[] elementosCadena = cadenaIngresada.split(";");
        for (int i = 0 ; i < elementosCadena.length ; i++) {
            elementosCadena[i] = elementosCadena[i].strip();
            System.out.println(elementosCadena[i]);
        }
        int enteros = 0;
        int decimales = 0;
        int vacios = 0;
        for (int i = 0 ; i < elementosCadena.length ; i++) {
            try {
                if (elementosCadena[i].isBlank()) {
                    vacios++;
                }
                else if (elementosCadena[i].contains(".")) {
                    Double.parseDouble(elementosCadena[i]);
                    decimales++;
                }
                else {
                    Integer.parseInt(elementosCadena[i]);
                    enteros++;
                }
            }
            catch (Exception error) {}
        }
        System.out.println("Enteros Válidos: "+enteros);
        System.out.println("Decimales Válidos: "+decimales);
        System.out.println("Entradas Vacias: "+vacios);
        sc.close();
    }
}
