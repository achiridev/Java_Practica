package com.achiridev.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import com.achiridev.model.Post;
import com.achiridev.model.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@DataJpaTest
public class PostRepositoryTest {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    

    @Test
    void deberiaBuscarTodosPageable() {
        // Arrange
        Usuario usuario01 = crearUsuario("Daniel", "daniel@gmail.com");
        Usuario usuario02 = crearUsuario("Pepito", "pepito@gmail.com");
        crearPost("Post 01 - De Daniel", usuario01);
        crearPost("Post 02 - De Pepito", usuario02);

        // Act
        Page<Post> postsEncontrados = postRepository.findAll(
            PageRequest.of(0, 2)
        );

        // Assert
        assertEquals(2, postsEncontrados.getContent().size());
        assertEquals(2, postsEncontrados.getTotalElements());
        assertEquals(1, postsEncontrados.getTotalPages());
        assertTrue(postsEncontrados.stream().anyMatch(p -> p.getTitulo().equals("Post 01 - De Daniel")));
        assertTrue(postsEncontrados.stream().anyMatch(p -> p.getTitulo().equals("Post 02 - De Pepito")));
    }

    @Test
    void deberiaBuscarPorTitulo() {
        // Arrange
        Usuario usuario01 = crearUsuario("Daniel", "daniel@gmail.com");
        crearPost("Post 01 - De Daniel", usuario01);
        crearPost("Post 02 - De Daniel", usuario01);

        // Act
        List<Post> postsEncontrados01 = postRepository.findByTituloContainingIgnoreCase("Daniel");
        List<Post> postsEncontrados02 = postRepository.findByTituloContainingIgnoreCase("POST 02");
        List<Post> postsEncontrados03 = postRepository.findByTituloContainingIgnoreCase("Post 03");

        // Assert
        assertEquals(2, postsEncontrados01.size());
        assertTrue(postsEncontrados01.stream().anyMatch(p -> p.getTitulo().equals("Post 02 - De Daniel")));
        assertEquals(1, postsEncontrados02.size());
        assertTrue(postsEncontrados02.stream().anyMatch(p -> p.getTitulo().equals("Post 02 - De Daniel")));
        assertEquals(0, postsEncontrados03.size());
    }

    @Test
    void deberiaObtenerLos5MasRecientes() throws InterruptedException {
        // Arrange
        Usuario usuario01 = crearUsuario("Daniel", "daniel@gmail.com");
        crearPostConDelay("Post 01 - De Daniel", usuario01);
        crearPostConDelay("Post 02 - De Daniel", usuario01);
        crearPostConDelay("Post 03 - De Daniel", usuario01);
        crearPostConDelay("Post 04 - De Daniel", usuario01);
        crearPostConDelay("Post 05 - De Daniel", usuario01);
        crearPostConDelay("Post 06 - De Daniel", usuario01);

        // Act
        List<Post> postsEncontrados = postRepository.findTop5OrderByFechaDesc();

        // Assert
        assertEquals(5, postsEncontrados.size());
        assertTrue(postsEncontrados.stream().anyMatch(p -> p.getTitulo().equals("Post 05 - De Daniel")));
        assertTrue(postsEncontrados.stream().noneMatch(p -> p.getTitulo().equals("Post 01 - De Daniel")));
    }

    private Usuario crearUsuario(String nombre, String email) {
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setPassword("1234");
        return usuarioRepository.save(u);
    }

    private Post crearPost(String titulo, Usuario usuario) {
        Post p = new Post();
        p.setTitulo(titulo);
        p.setUsuario(usuario);
        return postRepository.save(p);
    }

    private Post crearPostConDelay(String titulo, Usuario usuario) throws InterruptedException {
        Post post = crearPost(titulo, usuario);
        Thread.sleep(5);
        return post;
    }
}
