package proyecto03;

public class Main {
    public static void main(String[] args) {
        MotorValidacion motor = new MotorValidacion();
        
        Validador<Integer> edadMayorDeEdad = edad -> edad >= 18;
        Validador<Integer> edadJubilacion = edad -> edad >= 65;
        Validador<String> passwordFuerte = pwd -> pwd.length() >= 8 && pwd.matches(".*\\d.*");
        
        System.out.println(motor.ejecutarValidacion(25, edadMayorDeEdad)); // true
        System.out.println(motor.ejecutarValidacion(70, edadJubilacion));  // true
        System.out.println(motor.ejecutarValidacion("abc123", passwordFuerte)); // false
    }
}