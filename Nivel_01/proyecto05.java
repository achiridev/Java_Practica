import java.util.Arrays;

public class proyecto05 {
    public static void main(String[] args) {
        int[] arregloRecibido = {78, 95, 60, 88, 92};
        int numeroBuscado = 88;
        int[] arregloOrdenado = (Arrays.copyOf(arregloRecibido, arregloRecibido.length));
        Arrays.sort(arregloOrdenado);
        int posicionNumeroBuscado = Arrays.binarySearch(arregloOrdenado, numeroBuscado);
        System.out.println("Ranking ordenado: "+Arrays.toString(arregloOrdenado));
        if (posicionNumeroBuscado < 0) {
            System.out.println("Posicion del valor "+numeroBuscado+": No encontrado");
        }
        else {
            System.out.println("Posicion del valor "+numeroBuscado+": "+posicionNumeroBuscado);
        }
    }
}