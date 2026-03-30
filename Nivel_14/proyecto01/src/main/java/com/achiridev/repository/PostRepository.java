package com.achiridev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;  
import org.springframework.data.domain.Page;  
import org.springframework.data.repository.query.Param; 

import com.achiridev.model.Post;
import java.util.Optional;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findAll(Pageable pageable);

    List<Post> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT p FROM Post p ORDER BY p.fecha DESC LIMIT 5")
    List<Post> findTop5OrderByFechaDesc();

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.usuario LEFT JOIN FETCH p.comentarios WHERE p.id = :id")
    Optional<Post> findByIdWithRelations(@Param("id") Long id);
}
