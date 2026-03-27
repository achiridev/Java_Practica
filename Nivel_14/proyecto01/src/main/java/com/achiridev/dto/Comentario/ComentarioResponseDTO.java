package com.achiridev.dto.Comentario;

import com.achiridev.dto.Post.PostResponseDTO;
import com.achiridev.dto.Usuario.UsuarioResponseDTO;

public class ComentarioResponseDTO {

    private Long id;
    private String contenido;
    private UsuarioResponseDTO usuario;
    private PostResponseDTO post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

    public PostResponseDTO getPost() {
        return post;
    }

    public void setPost(PostResponseDTO post) {
        this.post = post;
    }
}
