package com.achiridev.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.user.model.Rol;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByNombre(String nombre);
}
