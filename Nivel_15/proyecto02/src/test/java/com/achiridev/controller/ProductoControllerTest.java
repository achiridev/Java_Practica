package com.achiridev.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.achiridev.config.SecurityConfig;
import com.achiridev.dto.ProductoCreateDTO;
import com.achiridev.dto.ProductoResponseDTO;
import com.achiridev.dto.ProductoResumeDTO;
import com.achiridev.extra.PageResponse;
import com.achiridev.security.jwt.JwtAuthenticationFilter;
import com.achiridev.service.ProductoService;

import jakarta.servlet.FilterChain;
import tools.jackson.databind.ObjectMapper;

import com.achiridev.fixtures.ProductoDTOFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(ProductoController.class)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        doAnswer(invocation -> {
            FilterChain chain = invocation.getArgument(2);
            chain.doFilter(invocation.getArgument(0), invocation.getArgument(1));
            return null;
        }).when(jwtAuthenticationFilter).doFilter(any(), any(), any());
    }

    @Test
    void deberiaListarProductos() throws Exception {

        List<ProductoResumeDTO> lista = List.of(
            ProductoDTOFactory.resumeDTO(),
            ProductoDTOFactory.resumeDTO()
        );

        PageResponse<ProductoResumeDTO> response =
            new PageResponse<>( lista, 0, 2, 10, 5 );

        when(productoService.findAll(any(Pageable.class)))
            .thenReturn(response);

        mockMvc.perform(
                get("/api/public/productos")
                    .param("page", "0")
                    .param("size", "2")
                )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").isNumber())
            .andExpect(jsonPath("$.content[0].nombre").value("Laptop"))
            .andExpect(jsonPath("$.page").value(0))
            .andExpect(jsonPath("$.size").value(2));
    }

    @Test
    @WithMockUser(
        authorities = {"ROLE_MODERATOR", "CREATE_PRODUCT"}
    )
    void deberiaCrearProducto() throws Exception {
        ProductoCreateDTO request = ProductoDTOFactory.createDTO();
        ProductoResponseDTO response = ProductoDTOFactory.responseDTO();

        when(productoService.save(any(ProductoCreateDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/mod/productos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nombre").value("Laptop"))
                .andExpect(jsonPath("$.precio").value(new BigDecimal("2500")))
                .andExpect(jsonPath("$.stock").value(100));
    }

    @Test
    @WithMockUser(
        authorities = {"ROLE_USER"}
    )
    void deberiaObtenerCompras() throws Exception{
        mockMvc.perform(get("/api/user/compras"))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(
        authorities = {"ROLE_MODERATOR", "UPDATE_PRODUCT"}
    )
    void deberiaActualizarProducto() throws Exception {
        ProductoCreateDTO request = ProductoDTOFactory.createDTO();
        ProductoResponseDTO response = ProductoDTOFactory.responseDTO();

        when(productoService.update(any(Long.class), any(ProductoCreateDTO.class))).thenReturn(response);

        mockMvc.perform(put("/api/mod/productos/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nombre").value("Laptop"))
                .andExpect(jsonPath("$.precio").value(new BigDecimal("2500")))
                .andExpect(jsonPath("$.stock").value(100));
    }

    @Test
    @WithMockUser(
        authorities = {"ROLE_ADMIN", "DELETE_PRODUCT"}
    )
    void deberiaEliminarProducto() throws Exception {
        mockMvc.perform(delete("/api/admin/productos/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void deberiaLanzarErrorSiNoTienePermiso() throws Exception {
        mockMvc.perform(delete("/api/admin/productos/{id}", 1L))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(
        authorities = {"ROLE_MODERATOR", "DELETE_PRODUCT"}
    )
    void deberiaLanzarErrorSiNoTieneRolAdminPeroSiTienePermiso() throws Exception {
        mockMvc.perform(delete("/api/admin/productos/{id}", 1L))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(
        authorities = {"ROLE_ADMIN"}
    )
    void deberiaLanzarErrorSiTieneRolAdminPeroNoTienePermiso() throws Exception {
        mockMvc.perform(delete("/api/admin/productos/{id}", 1L))
                .andExpect(status().isForbidden());
    }
}
