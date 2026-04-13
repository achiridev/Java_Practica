package com.achiridev.config;

import com.achiridev.model.Permiso;
import com.achiridev.model.Rol;
import com.achiridev.model.Usuario;
import com.achiridev.repository.RolRepository;
import com.achiridev.repository.PermisoRepository;
import com.achiridev.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataInitializer {

    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RolRepository rolRepository,
                            PermisoRepository permisoRepository,
                            UsuarioRepository usuarioRepository,
                            PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

/*
- Daniel, dachiri@unsa.edu.pe, danielPass (Roles : usuario y admin)
- Maria, maria@gmail.com, mariaPass (Roles : moderador)
- Pepito, pepito@gmail.com, pepitoPass (Roles : usuario)
*/

    @PostConstruct
    public void init() {

        if (rolRepository.count() > 0 && usuarioRepository.count() > 0) {
            return;
        }

        Permiso readProduct = crearPermiso("READ_PRODUCT");
        Permiso createProduct = crearPermiso("CREATE_PRODUCT");
        Permiso deleteProduct = crearPermiso("DELETE_PRODUCT");
        Permiso updateProduct = crearPermiso("UPDATE_PRODUCT");

        Rol admin = new Rol();
        admin.setNombre("ADMIN");
        admin.setPermisos(Set.of(readProduct, createProduct, deleteProduct, updateProduct));

        Rol mod = new Rol();
        mod.setNombre("MODERATOR");
        mod.setPermisos(Set.of(readProduct, createProduct, updateProduct));

        Rol user = new Rol();
        user.setNombre("USER");
        user.setPermisos(Set.of(readProduct));

        rolRepository.saveAll(Set.of(admin, mod, user));

        admin = rolRepository.findByNombre("ADMIN").orElseThrow();
        mod = rolRepository.findByNombre("MODERATOR").orElseThrow();
        user = rolRepository.findByNombre("USER").orElseThrow();

        if (usuarioRepository.count() > 0) {
            return;
        }

        Usuario daniel = new Usuario();
        daniel.setUsername("Daniel");
        daniel.setEmail("dachiri@unsa.edu.pe");
        daniel.setPassword(passwordEncoder.encode("danielPass"));
        daniel.setRoles(Set.of(user, admin));

        Usuario maria = new Usuario();
        maria.setUsername("Maria");
        maria.setEmail("maria@gmail.com");
        maria.setPassword(passwordEncoder.encode("mariaPass"));
        maria.setRoles(Set.of(mod));

        Usuario pepito = new Usuario();
        pepito.setUsername("Pepito");
        pepito.setEmail("pepito@gmail.com");
        pepito.setPassword(passwordEncoder.encode("pepitoPass"));
        pepito.setRoles(Set.of(user));

        usuarioRepository.saveAll(Set.of(daniel, maria, pepito));
    }

    private Permiso crearPermiso(String nombre) {
        Permiso permiso = new Permiso();
        permiso.setNombre(nombre);
        return permisoRepository.save(permiso);
    }
}