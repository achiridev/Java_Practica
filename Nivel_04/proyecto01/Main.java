package proyecto01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("PROYECTO 1 — Calculadora flexible");
        Scanner sc = new Scanner(System.in);
        boolean iterable = true;
        do {
            StringBuilder sb = new StringBuilder();
            sb.append("Seleccione una opcion: ").append("\n")
                    .append("1) Suma normal").append("\n")
                    .append("2) Resta normal").append("\n")
                    .append("3) Diferencia positiva").append("\n")
                    .append("0) Salir").append("\n");
            System.out.print(sb.toString());
            String eleccion = sc.nextLine();
            if (eleccion.equals("0")) break;
            System.out.print("Ingrese el primer numero: ");
            int num1 = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el segundo numero: ");
            int num2 = sc.nextInt();
            sc.nextLine();

            Integer resultado = switch (eleccion) {
                case "1" -> Calculadora.calcularSumaNormal(num1, num2);
                case "2" -> Calculadora.calcularRestaNormal(num1, num2);
                case "3" -> Calculadora.calcularDiferenciaPositiva(num1, num2);
                default -> {
                    System.out.println("Opcion no valida, por favor volver a ingresar.");
                    yield null;
                }
            };
            if (resultado != null) System.out.println("La respuesta es: "+resultado);
        }
        while (iterable);
        sc.close();
    }
}
