package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.achiridev.model.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    
}
