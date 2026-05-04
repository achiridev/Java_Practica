package com.achiridev.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.achiridev.model.Rol;
import com.achiridev.model.Usuario;
import com.achiridev.model.Permiso;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
@Import(UsuarioRepositoryTest.Config.class)
public class UsuarioRepositoryTest {
    
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final PasswordEncoder passwordEncoder;

    private Permiso permiso;
    private Rol rol;
    private Usuario usuario;
    
    @Autowired
    UsuarioRepositoryTest(UsuarioRepository usuarioRepository, RolRepository rolRepository,
            PermisoRepository permisoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @TestConfiguration
    static class Config {

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @BeforeEach
    void setUp() {
        permiso = new Permiso();
        permiso.setNombre("BUSCAR_USUARIO");
        permisoRepository.save(permiso);

        rol = new Rol();
        rol.setNombre("ADMIN");
        rol.setPermisos(Set.of(permiso));
        rolRepository.save(rol);

        usuario = new Usuario();
        usuario.setUsername("Daniel");
        usuario.setEmail("daniel@gmail.com");
        usuario.setPassword(passwordEncoder.encode("1234"));
        usuario.setRoles(Set.of(rol));
        usuarioRepository.save(usuario);
    }


    @Test
    void deberiaBuscarPorEmailCuandoExiste() {

        Optional<Usuario> resultado =
                usuarioRepository.findByEmail("daniel@gmail.com");

        assertTrue(resultado.isPresent());
        assertEquals("Daniel", resultado.get().getUsername());
        assertEquals("daniel@gmail.com", resultado.get().getEmail());
        assertTrue(passwordEncoder.matches("1234", resultado.get().getPassword()));
    }

    @Test
    void deberiaBuscarPorEmailCuandoNoExiste() {

        Optional<Usuario> resultado =
                usuarioRepository.findByEmail("maria@gmail.com");

        assertTrue(resultado.isEmpty());
    }
}
