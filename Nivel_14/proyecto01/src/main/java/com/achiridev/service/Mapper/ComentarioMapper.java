package com.achiridev.service.Mapper;

import com.achiridev.dto.Comentario.ComentarioRequestDTO;
import com.achiridev.dto.Comentario.ComentarioResponseDTO;
import com.achiridev.model.*;

import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    private final UsuarioMapper usuarioMapper;
    private final PostMapper postMapper;

    public ComentarioMapper(UsuarioMapper usuarioMapper, PostMapper postMapper) {
        this.usuarioMapper = usuarioMapper;
        this.postMapper = postMapper;
    }

    public Comentario toEntity(ComentarioRequestDTO dto, Usuario usuario, Post post) {
        if (dto == null) {
            return null;
        }
        Comentario comentario = new Comentario();
        comentario.setContenido(dto.getContenido());
        comentario.setUsuario(usuario);
        comentario.setPost(post);
        return comentario;
    }

    public ComentarioResponseDTO toDTO(Comentario comentario) {
        if (comentario == null) {
            return null;
        }
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setId(comentario.getId());
        dto.setContenido(comentario.getContenido());
        dto.setUsuario(usuarioMapper.toDTO(comentario.getUsuario()));
        dto.setPost(postMapper.toDTO(comentario.getPost()));
        return dto;
    }
}
