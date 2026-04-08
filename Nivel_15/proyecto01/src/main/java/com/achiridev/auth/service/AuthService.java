package com.achiridev.auth.service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.user.dto.UsuarioCreateDTO;
import com.achiridev.user.dto.UsuarioLoginDTO;
import com.achiridev.user.dto.UsuarioResponseDTO;
import com.achiridev.user.mapper.UsuarioMapper;
import com.achiridev.user.model.Rol;
import com.achiridev.user.model.Usuario;
import com.achiridev.user.repository.RolRepository;
import com.achiridev.user.repository.UsuarioRepository;
import com.achiridev.security.jwt.JwtService;

import org.springframework.security.authentication.AuthenticationManager;  
import org.springframework.security.core.Authentication;  
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    private final AuthenticationManager authenticationManager;  
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,
            PasswordEncoder passwordEncoder, RolRepository rolRepository, AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RecursoNoEncontradoException("Rol no encontrado"));

        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        usuario.setRoles(roles);

        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public String login(UsuarioLoginDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())  
                .toList();

        return jwtService.generateToken(
                userDetails.getUsername(),
                roles
        );
    }
}
