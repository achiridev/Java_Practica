package proyecto09;

import java.util.Objects;

public class Usuario {
    private String username;
    private String password;
    private String rol;

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(username).append(" (Rol: ").append(rol).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return true;
        Usuario u = (Usuario) o;
        return username.equals(u.getUsername());
    }
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
