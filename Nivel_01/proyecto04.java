import java.util.Scanner;
public class proyecto04 {
    static final String INICIO = "Operacion permitida: ";
    static final String FIN = "Descripcion: ";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una operacion (MySQL): ");
        String operacionIngresada = sc.nextLine();
        boolean valido = true;
        String descripcion = switch (operacionIngresada) {
            case "CREATE" -> "Creacion de recurso";
            case "INSERT" -> "Insertar un nuevo dato";
            case "DELETE" -> "Eliminacion de recurso";
            case "WHERE" -> "Establece una condicion";
            default -> {
                valido = false;
                yield "Opcion no valida";
            }
        };
        String mensaje = INICIO+valido+"\n"+FIN+descripcion;
        System.out.println(mensaje);
        sc.close();
    }
}
