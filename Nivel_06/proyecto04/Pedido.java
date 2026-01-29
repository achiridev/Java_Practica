package proyecto04;

public class Pedido {
    private int id;
    private double precio;
    private int stock;

    public Pedido(int id, double precio, int stock) {
        this.id = id;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        try {
            Thread.sleep(1000);
            return stock;
        }
        catch (Exception e) {
            System.out.println("Ocurrio un error al verificar stock.");
            return 0;
        }
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
