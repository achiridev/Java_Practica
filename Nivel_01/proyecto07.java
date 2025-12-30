import java.util.Arrays;
import java.util.Scanner;
public class proyecto07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] version01 = {10,20,30};
        System.out.println(Arrays.toString(version01));
        // Crear version
        try {
            System.out.println("Ingrese el tamaño de la nueva version: ");
            int nuevoTamanio = sc.nextInt();
            Integer[] version02 = Arrays.copyOf(version01, nuevoTamanio);
            for (int i = 0 ; i < version02.length ; i++) {
                version02[i] = (i+1)*10;
            }
            System.out.println("Nueva version creada exitosamente: ");
            System.out.println(Arrays.toString(version02));
        }
        catch (Exception e) {
            System.out.println("Error al crear la nueva version: ");
            System.err.println(e);
        }
        finally {
            sc.close();
        }
    }
}
