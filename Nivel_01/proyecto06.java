import java.util.Arrays;
import java.util.List;

public class proyecto06 {
    public static void main(String[] args) {
        String[] catalogo = {"A", "B", "C"};
        List<String> catalogoMejorado = Arrays.asList(catalogo);
        System.out.println("Catalogo cargado correctamente");
        try {
            catalogoMejorado.add("D");
            System.out.println("Intento de modificacion exitoso");
        }
        catch (UnsupportedOperationException error) {
            System.out.println("Intento de modificacion rechazado");
        }
        finally {
            System.out.println("Fin del programa.");
        }
    }
}
