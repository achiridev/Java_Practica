package com.achiridev.config;

import com.achiridev.model.Permiso;
import com.achiridev.model.Rol;
import com.achiridev.repository.RolRepository;
import com.achiridev.repository.PermisoRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataInitializer {

    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;

    public DataInitializer(RolRepository rolRepository,
                            PermisoRepository permisoRepository) {
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
    }

    @PostConstruct
    public void init() {

        if (rolRepository.count() > 0) {
            return; // evita duplicados
        }

        // 1. Crear permisos
        Permiso readProduct = crearPermiso("READ_PRODUCT");
        Permiso createProduct = crearPermiso("CREATE_PRODUCT");
        Permiso deleteProduct = crearPermiso("DELETE_PRODUCT");
        Permiso updateProduct = crearPermiso("UPDATE_PRODUCT");

        // 2. Crear roles
        Rol admin = new Rol();
        admin.setNombre("ADMIN");
        admin.setPermisos(Set.of(readProduct, createProduct, deleteProduct, updateProduct));

        Rol mod = new Rol();
        mod.setNombre("MODERATOR");
        mod.setPermisos(Set.of(readProduct, createProduct, updateProduct));

        Rol user = new Rol();
        user.setNombre("USER");
        user.setPermisos(Set.of(readProduct));

        // 3. Guardar
        rolRepository.saveAll(Set.of(admin, mod, user));
    }

    private Permiso crearPermiso(String nombre) {
        Permiso permiso = new Permiso();
        permiso.setNombre(nombre);
        return permisoRepository.save(permiso);
    }
}