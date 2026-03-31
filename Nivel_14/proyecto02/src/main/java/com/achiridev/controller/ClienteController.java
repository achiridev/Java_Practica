package com.achiridev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.dto.Cliente.ClienteResponseDTO;
import com.achiridev.dto.Cliente.CrearClienteRequestDTO;
import com.achiridev.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ClienteController {
    
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody @Valid CrearClienteRequestDTO cliente) {
        ClienteResponseDTO nuevo = clienteService.crearCliente(cliente);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @PostMapping("/cliente/login")
    public ResponseEntity<ClienteResponseDTO> login(@RequestBody CrearClienteRequestDTO credenciales) {
        ClienteResponseDTO cliente = clienteService.login(credenciales.getEmail(), credenciales.getPassword());
        return ResponseEntity.ok(cliente);
    }
    
}
