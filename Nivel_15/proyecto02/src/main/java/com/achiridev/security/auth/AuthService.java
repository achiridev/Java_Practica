package com.achiridev.security.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.achiridev.dto.UsuarioLoginDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.model.Rol;
import com.achiridev.model.Usuario;
import com.achiridev.security.jwt.JwtService;
import com.achiridev.repository.RolRepository;
import com.achiridev.repository.UsuarioRepository;
import com.achiridev.dto.UsuarioCreateDTO;
import com.achiridev.dto.UsuarioResponseDTO;
import com.achiridev.mapper.UsuarioMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService,
                   UsuarioRepository usuarioRepository, RolRepository rolRepository,
                   UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(UsuarioLoginDTO request) {

        // 🔥 1. VALIDAR CREDENCIALES (AQUÍ PASA TODO)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 🔥 2. OBTENER USUARIO AUTENTICADO
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
		return jwtService.generateToken(userDetails);
    }

    @Transactional
    public UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        Rol rolUser = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RecursoNoEncontradoException("Rol no encontrado"));

        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        usuario.setRoles(roles);

        return usuarioMapper.toResponseDTO(usuarioRepository.save(usuario));
    }
}