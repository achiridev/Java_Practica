package proyecto05;

import java.util.List;

public class Usuario {
    private String name;
    private String password;
    private List<String> permisos;
    
    public Usuario(String name, String password, List<String> permisos) {
        this.name = name;
        this.password = password;
        this.permisos = permisos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }
}
