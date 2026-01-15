package proyecto01;

public class Calculadora {
    private static Operacion sumaNormal; 
    private static Operacion restaNormal;
    private static Operacion diferenciaPositiva;

    static {
        sumaNormal = (a,b) -> a+b;
        restaNormal = (a,b) -> a-b;
        diferenciaPositiva = (a,b) -> {
            int resta = a-b;
            return Math.abs(resta);
        };
    }
    
    public static int calcularSumaNormal(int a, int b) {
        return sumaNormal.operar(a, b);
    }
    public static int calcularRestaNormal(int a, int b) {
        return restaNormal.operar(a, b);
    }
    public static int calcularDiferenciaPositiva(int a, int b) {
        return diferenciaPositiva.operar(a, b);
    }
    public static int calcular(Operacion o, int a, int b) {
        return o.operar(a,b);
    }
}
