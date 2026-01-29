package proyecto03;

public class Cuenta {
    private Object key;
    public Cuenta() {
        key = new Object();
    }

    public void transferir(Cuenta destino) {
        synchronized (this.key) {
            System.out.println("Se bloqueo la cuenta de origen");
            try { Thread.sleep(2000); } catch (Exception e) {}

            synchronized (destino.key) {
                System.out.println("Se bloqueó la cuenta destino");
                System.out.println("Transferencia completada");
            }
        }
    }
}
