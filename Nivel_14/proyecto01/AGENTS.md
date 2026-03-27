# AGENTS.md - Proyecto Nivel 14 - Sistema de Blog

## Proyecto: Sistema de Blog (Usuarios, Posts y Comentarios)

- **Framework**: Spring Boot 4.0.5
- **Java**: 21+
- **Base de datos**: MariaDB con JPA/Hibernate
- **Package base**: `com.achiridev`
- **Validación**: Jakarta Validation (Hibernate Validator)

---

## Comandos Maven

```bash
# Compilar
./mvnw compile

# Ejecutar tests
./mvnw test

# Ejecutar UNA SOLA clase de test
./mvnw test -Dtest=Proyecto01ApplicationTests

# Ejecutar UN SOLO método de test
./mvnw test -Dtest=Proyecto01ApplicationTests#contextLoads

# Empaquetar JAR (skip tests)
./mvnw package -DskipTests

# Ejecutar aplicación
./mvnw spring-boot:run

# Limpiar
./mvnw clean

# Build completo
./mvnw clean package
```

---

## Dependencias Clave (pom.xml)

| Dependencia | Propósito |
|-------------|-----------|
| `spring-boot-starter-data-jpa` | JPA/Hibernate para persistencia |
| `spring-boot-starter-webmvc` | Controladores REST |
| `mariadb-java-client` | Driver JDBC MariaDB |
| `spring-boot-starter-validation` | Validación con Jakarta Validation |
| `spring-boot-starter-data-jpa-test` | Tests de persistencia |
| `spring-boot-starter-webmvc-test` | Tests de controladores |

---

## Estructura del Proyecto

```
src/main/java/com/achiridev/
├── Proyecto01Application.java      # Main class con @SpringBootApplication
├── model/                           # Entidades JPA
│   ├── Usuario.java
│   ├── Post.java
│   └── Comentario.java
├── dto/                             # Data Transfer Objects
│   ├── Usuario/
│   │   ├── UsuarioRequestDTO.java
│   │   └── UsuarioResponseDTO.java
│   ├── Post/
│   │   ├── PostRequestDTO.java
│   │   └── PostResponseDTO.java
│   └── Comentario/
│       ├── ComentarioRequestDTO.java
│       └── ComentarioResponseDTO.java
├── repository/                      # Repositorios JPA
│   ├── UsuarioRepository.java
│   ├── PostRepository.java
│   └── ComentarioRepository.java
├── service/                         # Lógica de negocio
│   ├── Mapper/                      # Mappers (Entity <-> DTO)
│   │   ├── UsuarioMapper.java
│   │   ├── PostMapper.java
│   │   └── ComentarioMapper.java
│   └── UsuarioService.java
└── controller/                      # Endpoints REST (pendiente)
```

---

## Convenciones de Código Java

### Imports

Organizar en este orden con línea en blanco entre grupos:
1. `jakarta.validation.*` (validación)
2. `jakarta.persistence.*` (JPA)
3. `java.*`
4. `org.springframework.*`
5. `com.achiridev.*`

### Entidades JPA

- Usar `@Entity` y `@Table(name = "nombre_tabla")`
- `FetchType.LAZY` para relaciones `@OneToMany` y `@ManyToOne`
- Inicializar listas con `new ArrayList<>()` en la declaración
- Constructor sin argumentos obligatorio para JPA

```java
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}
```

### DTOs (Data Transfer Objects)

- Package: `com.achiridev.dto.<Entidad>/`
- Nombre: `<Entidad>RequestDTO` y `<Entidad>ResponseDTO`
- **RequestDTO**: usa `Long <nombre>Id` para relaciones (no objetos)
- **ResponseDTO**: usa DTOs anidados para relaciones (no entidades)

```java
// RequestDTO - recibe IDs
public class PostRequestDTO {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;
}

// ResponseDTO - devuelve DTOs anidados
public class PostResponseDTO {
    private Long id;
    private String titulo;
    private LocalDateTime fecha;
    private UsuarioResponseDTO usuario;  // DTO anidado, no entidad
}
```

### Mappers (Entity <-> DTO)

- Ubicación: `com.achiridev.service.Mapper/`
- Anotación: `@Component`
- Métodos: `toEntity()` y `toDTO()`
- Null-check obligatorio en ambos métodos
- Usar inyección por constructor

```java
@Component
public class PostMapper {

    private final UsuarioMapper usuarioMapper;

    public PostMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public Post toEntity(PostRequestDTO dto, Usuario usuario) {
        if (dto == null) return null;
        Post post = new Post();
        post.setUsuario(usuario)
        post.setTitulo(dto.getTitulo());
        return post;
    }

    public PostResponseDTO toDTO(Post post) {
        if (post == null) return null;
        PostResponseDTO dto = new PostResponseDTO();
        dto.setTitulo(post.getTitulo());
        dto.setUsuario(usuarioMapper.toDTO(post.getUsuario()));
        return dto;
    }
}
```

### Convenciones de Nombres

| Elemento | Convención | Ejemplo |
|----------|------------|---------|
| Clases | PascalCase | `Usuario`, `PostMapper` |
| DTOs | PascalCase + Suffix | `UsuarioRequestDTO`, `PostResponseDTO` |
| Variables | camelCase | `usuarioId`, `nombreCompleto` |
| Columnas BD | snake_case | `usuario_id`, `fecha_creacion` |
| Métodos | camelCase | `getById()`, `toDTO()` |
| Packages | lowercase | `com.achiridev.dto` |

---

## Validación con Jakarta Validation

En RequestDTOs usar anotaciones de validación:

```java
@NotBlank(message = "El campo es obligatorio")
@Email(message = "El email debe ser válido")
@Size(min = 2, max = 100, message = "El tamaño debe ser entre 2 y 100")
@NotNull(message = "El ID es obligatorio")
@Min(value = 1, message = "El valor mínimo es 1")
```

---

## Configuración (application.yaml)

```yaml
spring:
  application:
    name: proyecto01
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.mariadb.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

**Variables de entorno requeridas**:
- `DB_URL` - JDBC URL (ej: `jdbc:mariadb://localhost:3306/blog`)
- `DB_USERNAME` - Usuario de MariaDB
- `DB_PASSWORD` - Contraseña de MariaDB

---

## Pruebas

- Clase: `src/test/java/com/achiridev/Proyecto01ApplicationTests.java`
- Usar `@SpringBootTest` para tests de integración
- Usar JUnit 5 (`org.junit.jupiter.api.Test`)

---

## Notas Importantes

1. **ddl-auto: update** - Hibernate crea/actualiza tablas automáticamente
2. **FetchType.LAZY** - Las relaciones se cargan bajo demanda
3. **Constructor vacío** - Las entidades JPA lo requieren
4. **Mapper null-check** - Siempre verificar null antes de mapear
5. **RequestDTO usa IDs** - Para relaciones, recibir `Long <nombre>Id`, no objetos
6. **ResponseDTO usa DTOs** - Para relaciones, devolver DTOs anidados, no entidades
