package com.achiridev.proyecto03;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> buscarPorUsername(String username);  
    Usuario guardar(Usuario usuario);
}
