package com.achiridev.service.Mapper;

import com.achiridev.dto.Post.PostRequestDTO;
import com.achiridev.dto.Post.PostResponseDTO;
import com.achiridev.model.Post;
import com.achiridev.model.Usuario;

import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    private final UsuarioMapper usuarioMapper;

    public PostMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public Post toEntity(PostRequestDTO dto, Usuario usuario) {
        if (dto == null) {
            return null;
        }
        Post post = new Post();
        post.setUsuario(usuario);
        post.setTitulo(dto.getTitulo());
        return post;
    }

    public PostResponseDTO toDTO(Post post) {
        if (post == null) {
            return null;
        }
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitulo(post.getTitulo());
        dto.setFecha(post.getFecha());
        dto.setUsuario(usuarioMapper.toDTO(post.getUsuario()));
        return dto;
    }
}
