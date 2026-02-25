package com.achiridev.proyecto03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.achiridev.ServiceException;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void testCreacionUsuarioExitosa() {
        Usuario usuarioFalso = new Usuario("UsuarioTest");

        when(repository.buscarPorUsername("UsuarioTest"))
                .thenReturn(Optional.empty());
        when(repository.guardar(usuarioFalso))
                .thenReturn(usuarioFalso);
        
        Usuario resultado = service.crearUsuario("UsuarioTest");
        assertEquals("UsuarioTest", resultado.getUsername());

        verify(repository).guardar(usuarioFalso);
    }

    @Test
    void testCreacionUsuarioFallida() {
        Usuario usuarioFalso = new Usuario("UsuarioTest");

        when(repository.buscarPorUsername("UsuarioTest"))
                .thenReturn(Optional.of(usuarioFalso));
        
        ServiceException ex = assertThrows(
            ServiceException.class,
            () -> service.crearUsuario("UsuarioTest")
        );
        assertEquals("Usuario ya existe", ex.getMessage());
        verify(repository, never()).guardar(usuarioFalso);
    }
}
