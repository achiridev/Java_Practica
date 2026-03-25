# AGENTS.md - Proyecto02 Code Guidelines

This document provides guidelines for agents working in this Spring Boot repository.

## Project Overview

- **Java**: 21+
- **Spring Boot**: 4.0.4
- **Build Tool**: Maven (with wrapper `./mvnw`)
- **Architecture**: Controller -> Service -> Repository
- **Package Base**: `com.achiridev`
- **Location**: `Nivel_13/proyecto02/`

## Build & Test Commands

### Maven Wrapper (./mvnw)
```bash
# Compile
./mvnw compile

# Run all tests
./mvnw test

# Run single test class
./mvnw test -Dtest=Proyecto02ApplicationTests

# Run single test method
./mvnw test -Dtest=Proyecto02ApplicationTests#contextLoads

# Run all tests matching pattern
./mvnw test -Dtest="*Test"

# Package (JAR)
./mvnw package -DskipTests

# Run application
./mvnw spring-boot:run

# Clean
./mvnw clean

# Full build
./mvnw clean package

# Run with Spring profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Code Style Guidelines

### General Conventions
- **Language**: Java 21 (minimum)
- **Encoding**: UTF-8
- **Indentation**: 4 spaces (no tabs)
- **Line Length**: Under 120 characters when practical
- **Braces**: Opening brace on same line
- **Comments**: Spanish for user-facing messages, English for code

### Naming Conventions
| Element       | Convention       | Example                              |
|---------------|------------------|--------------------------------------|
| Classes       | PascalCase       | `UsuarioService`, `ErrorResponse`    |
| DTOs          | PascalCase + Suffix | `UsuarioRequestDTO`, `UsuarioResponseDTO` |
| Methods       | camelCase        | `getById()`, `registrar()`, `autenticar()` |
| Variables     | camelCase        | `usuarioRequestDTO`, `idCounter`     |
| Constants     | UPPER_SNAKE_CASE | `MAX_RETRY_COUNT`                    |
| Packages      | lowercase        | `com.achiridev`                     |
| Annotations   | camelCase        | `@RestController`, `@Service`        |
| YAML keys     | kebab-case       | `spring-application`                |

### Directory Structure
```
proyecto02/
├── pom.xml
├── AGENTS.md
└── src/
    ├── main/
    │   ├── java/com/achiridev/
    │   │   ├── Proyecto02Application.java
    │   │   ├── controller/
    │   │   │   └── UsuarioController.java
    │   │   ├── service/
    │   │   │   ├── UsuarioService.java
    │   │   │   └── UsuarioMapper.java
    │   │   ├── repository/
    │   │   │   └── UsuarioRepository.java
    │   │   ├── model/
    │   │   │   └── Usuario.java
    │   │   ├── dto/
    │   │   │   ├── UsuarioRequestDTO.java
    │   │   │   ├── UsuarioResponseDTO.java
    │   │   │   └── UsuarioLoginDTO.java
    │   │   └── exception/
    │   │       ├── GlobalExceptionHandler.java
    │   │       ├── ErrorResponse.java
    │   │       └── UsuarioNoEncontradoException.java
    │   └── resources/
    │       └── application.yaml
    └── test/
        └── java/com/achiridev/
            └── Proyecto02ApplicationTests.java
```

### Import Organization
Group imports in this order with blank lines between groups:
1. `java.*` imports
2. `jakarta.*` imports (validation)
3. Third-party imports (`org.springframework.*`)
4. Project imports (`com.achiridev.*`)

```java
package com.achiridev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.achiridev.service.UsuarioService;
import com.achiridev.dto.*;
```

### Class Structure
1. Package declaration
2. Import statements (grouped as above)
3. Class-level annotations
4. Class declaration
5. Private fields
6. Constructors
7. Public methods
8. Private methods
9. Getters/Setters

### Annotation Usage
| Annotation              | Purpose                              |
|-------------------------|--------------------------------------|
| `@SpringBootApplication`| Main application class               |
| `@RestController`        | REST API controllers                 |
| `@RequestMapping`        | URL path mapping                     |
| `@Service`              | Service layer classes                |
| `@Component`            | Generic Spring-managed beans (mappers)|
| `@Repository`           | Data access classes                  |
| `@RestControllerAdvice` | Global exception handling            |
| `@Valid`                | Input validation on DTOs             |

### DTOs and Validation
- Use `*RequestDTO` for input, `*ResponseDTO` for output
- Use Jakarta Validation annotations on DTOs
- Validation annotations: `@NotBlank`, `@Email`, `@Size`, `@Min`, `@Max`

```java
public class UsuarioRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;

    @Min(value = 18, message = "Debe ser mayor de edad")
    @Max(value = 100, message = "Edad no válida")
    private int edad;
}
```

### Model Classes (Entities)
- Plain Java classes with private fields
- No database annotations (in-memory HashMap repository)
- Getters and setters for all fields
- Default constructor required

```java
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private int edad;

    public Usuario() { }

    public Usuario(Long id, String nombre, String email, String password, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.edad = edad;
    }
    // getters and setters...
}
```

### Mapper Pattern
Use `@Component` for mapper classes that convert between entities and DTOs:

```java
@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        // ... other mappings
        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        // ... other mappings
        return dto;
    }
}
```

### Service Layer
- Constructor injection for dependencies
- Business logic and validation
- Throws custom exceptions for domain errors
- Return DTOs (not entities)

```java
@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO registrar(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(saved);
    }
}
```

### Controller Layer
- Use `@RestController` with `@RequestMapping`
- Return `ResponseEntity<?>` with appropriate HTTP status codes
- Use `@Valid` on DTO parameters

```java
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO response = usuarioService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
```

### Error Handling
- Use `@RestControllerAdvice` for global exception handling
- Create custom exception classes for domain errors
- Return `ErrorResponse` with status, message, and timestamp

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarNoEncontrado(UsuarioNoEncontradoException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> manejarArgumentoInvalido(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
```

### Testing Conventions
```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Proyecto02ApplicationTests {
    @Test
    void contextLoads() {
    }
}
```
- Test classes: `*Tests.java` in `src/test/java/`
- Use JUnit 5 (`org.junit.jupiter.api.Test`)
- Use `@SpringBootTest` for integration tests
- Use `assertTrue`, `assertEquals` from `org.junit.jupiter.api.Assertions`

## Important Notes
1. Always run `./mvnw test` before committing
2. Use constructor injection - avoid field injection
3. Return DTOs from controllers/services, not entities
4. Validate input with Jakarta Validation annotations
5. Use custom exceptions for domain-specific errors
6. Null-check in mapper methods before mapping
7. Prefer `Optional` for nullable return types in repository
