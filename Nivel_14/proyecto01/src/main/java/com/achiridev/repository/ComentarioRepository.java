package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
}
