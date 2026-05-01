package com.achiridev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
class Proyecto01ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deberiaGuardarYObtenerPedidos() throws Exception {
		mockMvc.perform(post("/api/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"cliente": "Juan",
							"total": 100.0,
							"estado": "PENDIENTE"
						}
						"""))
				.andExpect(status().isOk());
		mockMvc.perform(post("/api/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"cliente": "Maria",
							"total": 150.0,
							"estado": "ENVIADO"
						}
						"""))
				.andExpect(status().isOk());
		mockMvc.perform(post("/api/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"cliente": "Pedro",
							"total": 200.0,
							"estado": "PENDIENTE"
						}
						"""))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/pedidos/{id}", 2L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(2L))
				.andExpect(jsonPath("$.cliente").value("Maria"))
				.andExpect(jsonPath("$.total").value(150.0))
				.andExpect(jsonPath("$.estado").value("ENVIADO"));

		mockMvc.perform(get("/api/pedidos")
					.param("estado", "PENDIENTE"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].cliente").value("Pedro"))
				.andExpect(jsonPath("$.length()").value(2));
	}

}
