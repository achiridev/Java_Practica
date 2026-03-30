package com.achiridev.service;

import org.springframework.data.domain.Pageable;  
import org.springframework.data.domain.Page;  
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achiridev.dto.Post.PostRequestDTO;
import com.achiridev.dto.Post.PostResponseDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.model.Usuario;
import com.achiridev.model.Post;
import com.achiridev.repository.PostRepository;
import com.achiridev.repository.UsuarioRepository;
import com.achiridev.service.Mapper.PostMapper;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UsuarioRepository usuarioRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PostResponseDTO crearPost(PostRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID "+dto.getUsuarioId()));
        Post post = postMapper.toEntity(dto, usuario);
        post = postRepository.save(post);
        return postMapper.toDTO(post);
    }

    public Page<PostResponseDTO> listar(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::toDTO);
    }

    public List<PostResponseDTO> busquedaPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("titulo no puede estar vacio.");
        return postRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    public List<PostResponseDTO> buscar5Ultimos() {
        return postRepository.findTop5OrderByFechaDesc()
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponseDTO obtenerPorId(Long id) {
        Post post = postRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Post no encontrado con ID: " + id));
        return postMapper.toDTO(post);
    }
}
