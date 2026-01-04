package proyecto02;

import java.util.ArrayList;

public class Usuario {
    private static Integer contador_id = 1000;
    private ArrayList<Integer> listaIDs;
    private final Integer id;
    private String fechaRegistro;

    public Usuario(int id, String fechaRegistro) {
        if (!validarID(id)) {
            id = contador_id;
            contador_id++;
        }
        this.id = id;
        this.fechaRegistro = fechaRegistro;
    }
    public Usuario() {
        this(0, "hoy xd");
    }
    public boolean validarID(int id) {
        for (Integer id_lista : listaIDs) {
            if (id_lista == id) {
                return false;
            }
            else if (id_lista == 0) {
                return false;
            }
        }
    return true;
    }
    public Integer getId() {
        return this.id;
    }
    public String getFechaRegistro() {
        return this.fechaRegistro;
    }
}
