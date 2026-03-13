package com.achiridev.proyecto01;

import org.springframework.stereotype.Repository;
import java.util.Random;

@Repository
public class UsuarioRepository {
    public Usuario guardar(Usuario usuario) {
        System.out.println("Guardando usuario:\n" + usuario);
        long nuevoId = new Random().nextLong(1,1000);
        usuario.setId(nuevoId);
        return usuario;
    }
}
