package com.achiridev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.achiridev.model.Post;
import com.achiridev.model.Usuario;
import com.achiridev.repository.PostRepository;
import com.achiridev.repository.UsuarioRepository;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class Proyecto01ApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PostRepository postRepository;


	@Test
	void deberiaCrearUsuarioPostYComentario() throws Exception {
		MvcResult resultUsuario = mockMvc.perform(post("/api/usuario")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
						"nombre": "Juan",
						"email": "juan@gmail.com",
						"password": "123456"
					}
					"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.nombre").value("Juan"))
				.andExpect(jsonPath("$.email").value("juan@gmail.com"))
				.andReturn();

		Long idUsuario = extraerId(resultUsuario);
		
		MvcResult resultPost = mockMvc.perform(post("/api/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
						"titulo": "Post 01",
						"usuarioId": %d
					}
					""".formatted(idUsuario)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.titulo").value("Post 01"))
				.andReturn();

		Long idPost = extraerId(resultPost);
		
		mockMvc.perform(post("/api/comentario")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
						"contenido": "Comentario 01",
						"usuarioId": %d,
						"postId": %d
					}
					""".formatted(idUsuario, idPost)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.contenido").value("Comentario 01"));
	}

	@Test
	void deberiaListarPaginado() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNombre("Daniel");
		usuario.setEmail("daniel@gmail.com");
		usuario.setPassword("1234");
		usuario = usuarioRepository.save(usuario);

        for (int i = 1; i <= 10; i++) {
            Post post = new Post();
            post.setTitulo("Post " + i);
			post.setUsuario(usuario);
            postRepository.save(post);
        }

		mockMvc.perform(get("/api/posts")
				.param("page", "0")
				.param("size", "5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].titulo").value("Post 1"))
				.andExpect(jsonPath("$.content[4].titulo").value("Post 5"))
				.andExpect(jsonPath("$.size").value(5))
				.andExpect(jsonPath("$.totalElements").value(10));
	}

	private Long extraerId(MvcResult result) throws Exception {
		return objectMapper
				.readTree(result.getResponse().getContentAsString())
				.get("id")
				.asLong();
	}

}
