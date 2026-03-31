package com.achiridev.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.achiridev.dto.Cliente.ClienteResponseDTO;
import com.achiridev.dto.Cliente.CrearClienteRequestDTO;
import com.achiridev.exception.RecursoNoEncontradoException;
import com.achiridev.mapper.ClienteMapper;
import com.achiridev.repository.ClienteRepository;
import com.achiridev.model.Cliente;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponseDTO crearCliente(CrearClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO login(String email, String password) {
        Optional<Cliente> cliente = clienteRepository.findByEmailAndPassword(email, password);
        return cliente.map(clienteMapper::toDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Credenciales inválidas"));
    }
}
