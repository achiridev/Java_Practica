package com.achiridev.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.achiridev.dto.Post.PostRequestDTO;
import com.achiridev.dto.Post.PostResponseDTO;
import com.achiridev.dto.Usuario.UsuarioResponseDTO;
import com.achiridev.service.PostService;


@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    private PostResponseDTO postResponseDTO;
    private UsuarioResponseDTO usuarioResponseDTO;

    private static final Long POST_ID = 1L;
    private static final String POST_TITULO = "Post 01";

    @BeforeEach
    void setUp() {
        usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(1L);
        usuarioResponseDTO.setNombre("Daniel");
        usuarioResponseDTO.setEmail("daniel@gmail");

        postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(POST_ID);
        postResponseDTO.setTitulo(POST_TITULO);
        postResponseDTO.setFecha(LocalDateTime.of(2026, 5, 3, 14, 47));
        postResponseDTO.setUsuario(usuarioResponseDTO);
    }

    @Test
    void deberiaCrearExitosamente() throws Exception {

        when(postService.crearPost(any(PostRequestDTO.class))).thenReturn(postResponseDTO);

        mockMvc.perform(post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "titulo": "Post 01",
                        "usuarioId": 1
                    }
                """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(POST_ID))
                .andExpect(jsonPath("$.titulo").value(POST_TITULO))
                .andExpect(jsonPath("$.fecha").value("2026-05-03T14:47:00"));
    }

    @Test
    void deberiaListarPaginado() throws Exception {

        List<PostResponseDTO> lista = List.of(
            postResponseDTO
        );

        Page<PostResponseDTO> pagina =
            new PageImpl<>(
                lista,
                PageRequest.of(0, 5),
                10
            );

        when(postService.listar(any(Pageable.class)))
            .thenReturn(pagina);

        mockMvc.perform(get("/api/posts")
                .param("page", "0")
                .param("size", "2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].titulo").value(POST_TITULO))
            .andExpect(jsonPath("$.size").value(5))
            .andExpect(jsonPath("$.totalElements").value(10));
    }

    @Test
    void deberiaObtenerLos5MasRecientes() throws Exception {
        when(postService.buscar5Ultimos()).thenReturn(List.of(postResponseDTO));
        mockMvc.perform(get("/api/posts/recientes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].titulo").value(POST_TITULO));
    }

    @Test
    void deberiaObteneerPorId() throws Exception {
        when(postService.obtenerPorId(POST_ID)).thenReturn(postResponseDTO);

        mockMvc.perform(get("/api/post/{id}", POST_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(POST_ID))
            .andExpect(jsonPath("$.titulo").value(POST_TITULO));
    }
}
