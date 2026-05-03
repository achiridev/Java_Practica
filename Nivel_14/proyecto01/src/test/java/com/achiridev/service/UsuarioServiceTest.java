package com.achiridev.service;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.achiridev.model.Usuario;
import com.achiridev.dto.Usuario.UsuarioRequestDTO;
import com.achiridev.dto.Usuario.UsuarioResponseDTO;
import com.achiridev.service.Mapper.UsuarioMapper;
import com.achiridev.repository.UsuarioRepository;
import com.achiridev.exception.RecursoNoEncontradoException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deberiaCrearYObtenerPorId() {
        // Arrange
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Daniel");
        dto.setEmail("daniel@gmail.com");
        dto.setPassword("1234");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Daniel");
        usuario.setEmail("daniel@gmail.com");
        usuario.setPassword("1234");

        UsuarioResponseDTO expectedResponse = new UsuarioResponseDTO();
        expectedResponse.setId(1L);
        expectedResponse.setNombre("Daniel");
        expectedResponse.setEmail("daniel@gmail.com");

        when(usuarioMapper.toEntity(dto)).thenReturn(usuario);
        when(usuarioRepository.existsByEmail("daniel@gmail.com")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(expectedResponse);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.ofNullable(usuario));

        // Act
        UsuarioResponseDTO responseCreacion = usuarioService.crearUsuario(dto);
        UsuarioResponseDTO responseBusqueda = usuarioService.obtenerPorId(1L);

        // Assert
        assertThrows(RecursoNoEncontradoException.class, () -> {
            usuarioService.obtenerPorId(2L);
        });

        assertEquals("Daniel", responseCreacion.getNombre());
        assertEquals("daniel@gmail.com", responseCreacion.getEmail());
        assertEquals(1L, responseCreacion.getId());

        assertEquals("Daniel", responseBusqueda.getNombre());
        assertEquals("daniel@gmail.com", responseBusqueda.getEmail());
        assertEquals(1L, responseBusqueda.getId());


        verify(usuarioMapper).toEntity(dto);
        verify(usuarioRepository).existsByEmail("daniel@gmail.com");
        verify(usuarioRepository).save(any(Usuario.class));
        verify(usuarioMapper, times(2)).toDTO(usuario);
    }
}
