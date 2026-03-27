package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}
