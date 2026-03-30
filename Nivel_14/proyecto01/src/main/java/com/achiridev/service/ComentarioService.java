package com.achiridev.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achiridev.model.*;
import com.achiridev.dto.Comentario.ComentarioRequestDTO;
import com.achiridev.dto.Comentario.ComentarioResponseDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.repository.ComentarioRepository;
import com.achiridev.repository.PostRepository;
import com.achiridev.repository.UsuarioRepository;
import com.achiridev.service.Mapper.ComentarioMapper;

@Service
public class ComentarioService {
    
    private final ComentarioRepository comentarioRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    private final ComentarioMapper comentarioMapper;

    public ComentarioService(ComentarioRepository comentarioRepository, PostRepository postRepository,
            UsuarioRepository usuarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    @Transactional
    public ComentarioResponseDTO crearComentario(ComentarioRequestDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Post no encontrado con ID "+dto.getPostId()));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID "+dto.getUsuarioId()));
        Comentario comentario = comentarioMapper.toEntity(dto, usuario, post);
        comentario = comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(comentario);
    }

    @Transactional(readOnly = true)
    public ComentarioResponseDTO obtenerPorId(Long id) {
        Comentario comentario = comentarioRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Comentario no encontrado con ID: " + id));
        return comentarioMapper.toDTO(comentario);
    }
}
