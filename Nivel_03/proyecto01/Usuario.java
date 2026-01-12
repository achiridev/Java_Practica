package proyecto01;

import java.util.Objects;

public class Usuario {
    private String username;
    private String password;
    private boolean activo;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        activo = true;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActivo() { return activo; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario { Username: ").append(username)
                .append(", Activo: ").append(activo).append("}");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario u = (Usuario) o;
        return u.getUsername().equals(this.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}
