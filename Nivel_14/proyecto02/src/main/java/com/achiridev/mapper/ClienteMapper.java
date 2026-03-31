package com.achiridev.mapper;

import org.springframework.stereotype.Component;

import com.achiridev.dto.Cliente.ClienteResponseDTO;
import com.achiridev.dto.Cliente.CrearClienteRequestDTO;
import com.achiridev.model.Cliente;

@Component
public class ClienteMapper {
    public Cliente toEntity(CrearClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombres(dto.getNombres());
        cliente.setEmail(dto.getEmail());
        cliente.setPassword(dto.getPassword());
        return cliente;
    }
    public ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNombres(cliente.getNombres());
        dto.setEmail(cliente.getEmail());
        return dto;
    }
}
