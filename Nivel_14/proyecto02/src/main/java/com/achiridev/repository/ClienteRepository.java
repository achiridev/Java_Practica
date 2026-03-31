package com.achiridev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achiridev.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmailAndPassword(String email, String password);
}
