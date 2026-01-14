package proyecto07;

import java.util.Objects;

public class Correo {
    private String email;
    private String proveedor;

    public Correo(String email, String proveedor) {
        this.email = email;
        this.proveedor = proveedor;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Correo { Email: ").append(email)
                .append(", Proveedor: ").append(proveedor)
                .append(" }");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correo correo = (Correo) o;
        return email.equals(correo.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}   
