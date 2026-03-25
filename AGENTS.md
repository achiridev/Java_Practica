# ProyectoJava - Repo General

## Project Overview
- Java 21+, Spring Boot 4.0.4
- Maven-based project structure
- Arquitectura: Controller -> Service -> Repository
- Estructura: Nivel_XX/proyectoYY/
- Package base: `com.achiridev`

## Build & Test Commands

### Maven Wrapper (./mvnw)
```bash
# Compile
./mvnw compile

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=NombreTest

# Run a single test method
./mvnw test -Dtest=NombreTest#nombreMetodo

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
| YAML keys     | kebab-case       | `spring-application`                |

### Import Organization
Group imports in this order with blank lines between groups:
1. `java.*` imports
2. `jakarta.*` imports (validation)
3. Third-party imports (`org.springframework.*`)
4. Project imports (`com.achiridev.*`)

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

### Architecture Patterns

#### Controller Layer
- Use `@RestController` with `@RequestMapping`
- Return `ResponseEntity<?>` with appropriate HTTP status codes
- Use `@Valid` on DTO parameters
- Method names in English (e.g., `register`, `getById`, `delete`)

#### Service Layer
- Constructor injection for dependencies
- Business logic and validation
- Throws custom exceptions for domain errors
- Return DTOs (not entities)
- Method names can be in Spanish (e.g., `registrar`, `autenticar`)

#### Repository Layer
- In-memory storage using HashMap
- Auto-increment ID with `idCounter`
- Return `Optional<T>` for nullable results
- Method names: `findById`, `findByEmail`, `existsByEmail`, `save`, `deleteById`

### DTOs and Validation
- Use `*RequestDTO` for input, `*ResponseDTO` for output
- Use Jakarta Validation annotations on DTOs
- Validation annotations: `@NotBlank`, `@Email`, `@Size`, `@Min`, `@Max`

### Error Handling
- Use `@RestControllerAdvice` for global exception handling
- Create custom exception classes for domain errors
- Return `ErrorResponse` with status, message, and timestamp

### Mapper Pattern
- Use `@Component` for mapper classes
- Methods: `toEntity()` and `toDTO()`
- Always null-check parameters before mapping

### Testing Conventions
- Test classes: `*Tests.java` in `src/test/java/`
- Use JUnit 5 (`org.junit.jupiter.api.Test`)
- Use `@SpringBootTest` for integration tests
- Use `assertTrue`, `assertEquals` from `org.junit.jupiter.api.Assertions`

## Project-Specific AGENTS.md Files
Each project has its own AGENTS.md with more detailed guidelines:
- `Nivel_12/proyecto01/AGENTS.md` - Configuration API with profiles
- `Nivel_13/proyecto02/AGENTS.md` - REST API with DTOs and validation

## Important Notes
1. Always run `./mvnw test` before committing
2. Use constructor injection - avoid field injection
3. Return DTOs from controllers/services, not entities
4. Validate input with Jakarta Validation annotations
5. Use custom exceptions for domain-specific errors
6. Null-check in mapper methods before mapping
7. Prefer `Optional` for nullable return types in repository
