package proyecto01;

import java.util.ArrayList;
import java.util.List;

public class Repositorio<T> {
    private List<T> listaEntidades;

    public Repositorio() {
        this.listaEntidades = new ArrayList<>();
    }
    public boolean guardar(T entidad) {
        try {
            listaEntidades.add(entidad);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public void eliminar(T objetoEliminar) {
        for (T entidad : listaEntidades) {
            if (entidad.equals(objetoEliminar)) {
                listaEntidades.remove(objetoEliminar);
            }
        }
    }
    public List<T> listar() {
        return listaEntidades;
    }
}
