package com.achiridev;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import com.achiridev.dto.UsuarioCreateDTO;
import com.achiridev.dto.UsuarioLoginDTO;
import com.achiridev.fixtures.ProductoDTOFactory;

import tools.jackson.databind.ObjectMapper;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class Proyecto02ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String USUARIO_NOMBRE = "Daniel Achiri";
	private static final String USUARIO_EMAIL  = "dachiri@gmail.com";
	private static final String USUARIO_PASSWORD = "danielPass";

	@Test
	void deberiaRegistrarseEIniciarSesionExitosamente() throws Exception {
		UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
		usuarioCreateDTO.setUsername(USUARIO_NOMBRE);
		usuarioCreateDTO.setEmail(USUARIO_EMAIL);
		usuarioCreateDTO.setPassword(USUARIO_PASSWORD);

		mockMvc.perform(post("/auth/register")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(usuarioCreateDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.username").value(USUARIO_NOMBRE))
				.andExpect(jsonPath("$.email").value(USUARIO_EMAIL));

		UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
		usuarioLoginDTO.setEmail(USUARIO_EMAIL);
		usuarioLoginDTO.setPassword(USUARIO_PASSWORD);

		mockMvc.perform(post("/auth/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(usuarioLoginDTO))
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").exists());
	}

	@Test
	void deberiaLanzarErrorSiNoEstaRegistrado() throws Exception {
		UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
		usuarioLoginDTO.setEmail(USUARIO_EMAIL);
		usuarioLoginDTO.setPassword(USUARIO_PASSWORD);

		mockMvc.perform(post("/auth/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(usuarioLoginDTO))
				)
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(
		authorities = {"ROLE_ADMIN", "CREATE_PRODUCT"}
	)
    void deberiaListarProductos() throws Exception {
		ProductoDTOFactory.createDTOs().stream()
			.map(productoCreateDTO -> objectMapper.writeValueAsString(productoCreateDTO))
			.forEach(producto -> {
				try {
					mockMvc.perform(post("/api/mod/productos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(producto))
						.andExpect(status().isCreated());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

        mockMvc.perform(
                get("/api/public/productos")
                    .param("page", "0")
                    .param("size", "5")
                )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").isNumber())
            .andExpect(jsonPath("$.content[0].nombre").value("Producto 1"))
            .andExpect(jsonPath("$.page").value(0))
            .andExpect(jsonPath("$.size").value(5))
			.andExpect(jsonPath("$.totalElements").value(10));
    }

	@Test
	@WithMockUser(
		authorities = {"ROLE_USER"}
	)
	void deberiaObtenerCompras() throws Exception{
		mockMvc.perform(get("/api/user/compras"))
				.andExpect(status().isOk());
	}

	void deberiaObtenerCarrito() throws Exception{
		mockMvc.perform(get("/api/user/compras"))
				.andExpect(status().isForbidden());
	}
}
