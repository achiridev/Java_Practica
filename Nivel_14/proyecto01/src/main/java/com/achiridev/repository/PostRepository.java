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

    Optional<Post> findByTituloContainingIgnoreCase(String titulo);

    List<Post> findTop5OrderByFechaDesc();

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comentarios WHERE p.id = :id")
    Optional<Post> findByIdWithComentarios(@Param("id") Long id);
}
