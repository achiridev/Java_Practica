package proyecto02;

public class ContadorVisitas {
    private int visitas;
    private Object llave;

    public ContadorVisitas() {
        visitas = 0;
        llave = new Object();
    }

    public void incrementarVisita() {
        synchronized (llave) {
            visitas ++;
        }
    }
    
    public int getVisitas() {
        return visitas;
    }
}
