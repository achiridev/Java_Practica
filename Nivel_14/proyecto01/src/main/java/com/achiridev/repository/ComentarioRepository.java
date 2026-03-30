package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.achiridev.model.Comentario;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
    @Query("SELECT c FROM Comentario c JOIN FETCH c.usuario JOIN FETCH c.post WHERE c.id = :id")
    Optional<Comentario> findByIdWithRelations(@Param("id") Long id);
}
