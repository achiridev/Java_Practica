package com.achiridev.repository;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.achiridev.model.Usuario;

@Repository
public class UsuarioRepository {
    private HashMap<Long, Usuario> usuarios;
    private Long idCounter;

    public UsuarioRepository() {
        usuarios = new HashMap<>();
        idCounter = 1L;
        inicializarDatos();
    }
    
    public Usuario save(Usuario usuario){
        if (usuario.getId() == null) {
            usuario.setId(idCounter++);
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarios.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public boolean existsById(Long id) {
        return usuarios.containsKey(id);
    }

    public boolean existsByEmail(String email) {
        return usuarios.values().stream()
                .anyMatch(u -> u.getEmail().equals(email));
    }

    public void deleteById(Long id) {
        usuarios.remove(id);
    }

    public void inicializarDatos() {
        save(new Usuario(null, "Juan Pérez", "juanperez@example.com", "password123", 25));
        save(new Usuario(null, "María Gómez", "mariagomez@example.com", "password456", 30));
        save(new Usuario(null, "Carlos López", "carloslopez@example.com", "password789", 35));
        save(new Usuario(null, "Ana Martínez", "anamartinez@example.com", "password012", 28));
        save(new Usuario(null, "Luis Rodríguez", "luisrodriguez@example.com", "password345", 40));
        save(new Usuario(null, "Sofía Hernández", "sofiahernandez@example.com", "password678", 32));
        save(new Usuario(null, "Miguel Sánchez", "miguelsanchez@example.com", "password901", 26));
        save(new Usuario(null, "Isabel Torres", "isabeltorres@example.com", "password234", 38));
        save(new Usuario(null, "Diego Ramírez", "diegoramirez@example.com", "password567", 42));
        save(new Usuario(null, "Daniel Achiri", "dachiri@unsa.edu.pe", "danielPass", 18));
    }
}
