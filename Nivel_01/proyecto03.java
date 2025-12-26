import java.util.Scanner;
public class proyecto03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el texto: ");
        String textoIngresado = sc.nextLine();
        if (textoIngresado.strip().equals("")) {
            System.out.println("Texto no valido.");   
        }
        String[] palabras = textoIngresado.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            if (palabra.isBlank()) {continue;}
            sb.append(palabra.toLowerCase()).append(" ");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}
