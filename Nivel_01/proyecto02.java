import java.util.Scanner;
public class proyecto02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eleccion;
        while (true) {
            System.out.println("Ingrese un modo: \n1)ineficiente \n2)optimizada\n0)Salir");
            eleccion = sc.nextLine();
            switch (eleccion) {
                case "1":
                    formaIneficiente();
                    break;
                case "2":
                    formaOptimizada();
                    break;
                case "0":
                    sc.close();
                    System.out.println("Gracias por usar este programa.");
                    return;
                default:
                    System.out.println("\""+eleccion+"\" no es valido. Por favor volver a ingresar.");
                    break;
            }
        }
    }
    public static void formaIneficiente() {
        String cadena = "";
        for (int i = 1 ; i <= 1000 ; i++) {
            cadena += "Linea "+i+": procesamiento correcto.\n";
        }
        System.out.println(cadena);
    }
    public static void formaOptimizada() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= 1000 ; i++) {
            sb.append("Linea ").append(i).append(": procesamiento correcto.").append("\n");
        }
        System.out.println(sb.toString());
    }
}
