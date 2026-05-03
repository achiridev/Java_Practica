package com.achiridev.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.achiridev.repository.PostRepository;
import com.achiridev.repository.UsuarioRepository;
import com.achiridev.service.Mapper.PostMapper;

import com.achiridev.dto.Post.*;
import com.achiridev.dto.Usuario.UsuarioResponseDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.model.Post;
import com.achiridev.model.Usuario;

import org.springframework.data.domain.Pageable;  
import org.springframework.data.domain.Page;  

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    
    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostService postService;

    private Usuario usuario;
    private UsuarioResponseDTO usuarioResponseDTO;
    private Post post;
    private PostRequestDTO postRequestDTO;
    private PostResponseDTO postResponseDTO;
    
    private static final Long          USUARIO_ID       = 1L;
    private static final String        USUARIO_NOMBRE   = "Daniel";
    private static final String        USUARIO_EMAIL    = "daniel@gmail.com";
    private static final String        USUARIO_PASSWORD = "1234";
    private static final Long          POST_ID          = 1L;
    private static final String        POST_TITULO      = "Mi primer post";
    private static final LocalDateTime FECHA            = LocalDateTime.of(2026, 5, 2, 16, 9);

    @BeforeEach
    void setUp() {

        usuario = new Usuario();
        usuario.setId(USUARIO_ID);
        usuario.setNombre(USUARIO_NOMBRE);
        usuario.setEmail(USUARIO_EMAIL);
        usuario.setPassword(USUARIO_PASSWORD);

        usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(USUARIO_ID);
        usuarioResponseDTO.setNombre(USUARIO_NOMBRE);
        usuarioResponseDTO.setEmail(USUARIO_EMAIL);

        postRequestDTO = new PostRequestDTO();
        postRequestDTO.setUsuarioId(USUARIO_ID);
        postRequestDTO.setTitulo(POST_TITULO);

        post = new Post();
        post.setId(POST_ID);
        post.setUsuario(usuario);
        post.setTitulo(POST_TITULO);

        postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(POST_ID);
        postResponseDTO.setUsuario(usuarioResponseDTO);
        postResponseDTO.setTitulo(POST_TITULO);
        postResponseDTO.setFecha(FECHA);
    }

    @Test
    void deberiaCrearPostExitosamente() {
        // Arrange
        when(usuarioRepository.findById(USUARIO_ID)).thenReturn(Optional.ofNullable(usuario));
        when(postMapper.toEntity(postRequestDTO, usuario)).thenReturn(post);
        when(postRepository.save(post)).thenReturn(post);
        when(postMapper.toDTO(post)).thenReturn(postResponseDTO);

        // Act
        PostResponseDTO responseCreacion = postService.crearPost(postRequestDTO);

        // Assert
        assertEquals(postResponseDTO, responseCreacion);
    }

    @Test
    void deberiaLanzarExcepcionSiUsuarioNoExiste() {
        // Arrange
        when(usuarioRepository.findById(USUARIO_ID)).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNoEncontradoException ex = assertThrows(
            RecursoNoEncontradoException.class,
            () -> postService.crearPost(postRequestDTO)
        );

        assertEquals("Usuario no encontrado con ID " + USUARIO_ID, ex.getMessage());
        verify(postRepository, never()).save(any());
        verify(postMapper, never()).toEntity(any(), any());
    }

    @Test
    void deberiaRetornarPaginaDePostsExitosamente() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> paginaPosts = new PageImpl<>(
            List.of(post),
            pageable,
            1
        );
        when(postRepository.findAll(pageable)).thenReturn(paginaPosts);
        when(postMapper.toDTO(post)).thenReturn(postResponseDTO);

        // Act
        Page<PostResponseDTO> resultado = postService.listar(pageable);

        // Assert
        assertEquals(1, resultado.getTotalElements());
        assertEquals(1, resultado.getTotalPages());
        assertEquals(0, resultado.getNumber());
        assertEquals(10, resultado.getSize());
        PostResponseDTO primerPost = resultado.getContent().get(0);
        assertEquals(POST_ID, primerPost.getId());
        assertEquals(POST_TITULO, primerPost.getTitulo());

        verify(postMapper, times(1)).toDTO(post);
    }

    @Test
    void deberiaBuscarPorTitulo() {
        // Arrange
        String tituloBuscado = "Mi";
        when(postRepository.findByTituloContainingIgnoreCase(tituloBuscado)).thenReturn(List.of(post));
        when(postMapper.toDTO(post)).thenReturn(postResponseDTO);

        // Act
        List<PostResponseDTO> postsEncontrados = postService.busquedaPorTitulo(tituloBuscado);

        // Assert
        assertEquals(1, postsEncontrados.size());
        PostResponseDTO postEncontrado = postsEncontrados.get(0);
        assertEquals(POST_ID, postEncontrado.getId());
        assertEquals(POST_TITULO, postEncontrado.getTitulo());
    }
    @Test
    void deberiaBuscarPorTituloYRetornarListaVaciaSiNoHayCoincidencias() {
        // Arrange
        when(postRepository.findByTituloContainingIgnoreCase("xyz"))
            .thenReturn(List.of());

        // Act
        List<PostResponseDTO> resultado = postService.busquedaPorTitulo("xyz");

        // Assert
        assertTrue(resultado.isEmpty());
        verify(postMapper, never()).toDTO(any());
    }

    @Test
    void deberiaObtenerLos5MasRecientes() {
        // Arrange
        when(postRepository.findTop5OrderByFechaDesc()).thenReturn(List.of(post));
        when(postMapper.toDTO(post)).thenReturn(postResponseDTO);

        // Act
        List<PostResponseDTO> postsEncontrados = postService.buscar5Ultimos();

        // Assert
        assertEquals(1, postsEncontrados.size());
        PostResponseDTO postEncontrado = postsEncontrados.get(0);
        assertEquals(POST_ID, postEncontrado.getId());
        assertEquals(POST_TITULO, postEncontrado.getTitulo());
    }

    @Test
    void deberiaRetornarListaVaciaSiNoHayPostsRecientes() {
        // Arrange
        when(postRepository.findTop5OrderByFechaDesc()).thenReturn(List.of());

        // Act
        List<PostResponseDTO> resultado = postService.buscar5Ultimos();

        // Assert
        assertTrue(resultado.isEmpty());
        verify(postMapper, never()).toDTO(any());
    }
}
