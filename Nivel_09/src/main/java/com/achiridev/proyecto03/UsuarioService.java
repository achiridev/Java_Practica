package com.achiridev.proyecto03;

import com.achiridev.ServiceException;

public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario crearUsuario(String username) {
        if (repository.buscarPorUsername(username).isPresent()) {
            throw new ServiceException("Usuario ya existe");
        }

        return repository.guardar(new Usuario(username));
    }
}
