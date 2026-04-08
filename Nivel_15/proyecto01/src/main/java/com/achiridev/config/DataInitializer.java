package com.achiridev.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.achiridev.user.repository.RolRepository;
import com.achiridev.user.model.Rol;

@Configuration
public class DataInitializer {

    private final RolRepository rolRepository;

    public DataInitializer(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            crearRolSiNoExiste("ROLE_USER");
            crearRolSiNoExiste("ROLE_ADMIN");
        };
    }

    private void crearRolSiNoExiste(String nombre) {
        rolRepository.findByNombre(nombre)
                .orElseGet(() -> rolRepository.save(new Rol(null, nombre)));
    }
}