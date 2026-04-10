package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.achiridev.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    
}
