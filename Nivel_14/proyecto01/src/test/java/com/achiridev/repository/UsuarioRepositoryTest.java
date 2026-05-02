package com.achiridev.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.achiridev.model.Usuario;


@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deberiaBuscarPorEmail() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Daniel");
        usuario.setEmail("daniel@gmail.com");
        usuario.setPassword("1234");

        usuarioRepository.save(usuario);

        Boolean resultado =
                usuarioRepository.existsByEmail("daniel@gmail.com");

        assertTrue(resultado);
    }
}
