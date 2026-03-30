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
│   ├── UsuarioService.java
│   ├── PostService.java
│   └── ComentarioService.java
├── controller/                      # Endpoints REST
│   ├── UsuarioController.java
│   ├── PostController.java
│   └── ComentarioController.java
└── exception/                       # Manejo de errores
    ├── RecursoNoEncontradoException.java
    ├── EmailDuplicadoException.java
    ├── ErrorResponse.java
    └── GlobalExceptionHandler.java
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
- Usar `@CreationTimestamp` para fechas automáticas

### DTOs (Data Transfer Objects)

- Package: `com.achiridev.dto.<Entidad>/`
- Nombre: `<Entidad>RequestDTO` y `<Entidad>ResponseDTO`
- **RequestDTO**: usa `Long <nombre>Id` para relaciones (no objetos)
- **ResponseDTO**: usa DTOs anidados para relaciones (no entidades)

### Mappers (Entity <-> DTO)

- Ubicación: `com.achiridev.service.Mapper/`
- Anotación: `@Component`
- Métodos: `toEntity()` y `toDTO()`
- Null-check obligatorio en ambos métodos
- Usar inyección por constructor

### Services

- Ubicación: `com.achiridev.service/`
- Anotación: `@Service`
- Usar inyección por constructor
- Manejar excepciones personalizadas del paquete `exception`
- Devolver DTOs, nunca entidades
- Usar `@Transactional` para mantener la sesión de Hibernate abierta
- Métodos de lectura usar `@Transactional(readOnly = true)`

### Controllers

- Ubicación: `com.achiridev.controller/`
- Usar `@Valid` en RequestDTOs para validación
- Devolver `ResponseEntity<?>` con códigos HTTP apropiados

### Excepciones Personalizadas

- Ubicación: `com.achiridev.exception/`
- Crear excepciones que extiendan `RuntimeException`
- Usar `@RestControllerAdvice` para manejo global
- Manejar: `RecursoNoEncontradoException`, validaciones, errores generales

### Repositorios

- Extender `JpaRepository<Entidad, Long>`
- Usar `@Query` para consultas complejas (no nombres de método largos)
- Para paginación usar `Page<Entidad>` con `Pageable`
- Usar `JOIN FETCH` para cargar relaciones LAZY en una sola query

```java
@Query("SELECT p FROM Post p JOIN FETCH p.usuario WHERE p.id = :id")
Optional<Post> findByIdWithRelations(@Param("id") Long id);
```

---

## Validación con Jakarta Validation

```java
@NotBlank(message = "El campo es obligatorio")
@Email(message = "El email debe ser válido")
@Size(min = 2, max = 100)
@NotNull(message = "El ID es obligatorio")
```

---

## Endpoints API

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/usuario` | Crear usuario |
| GET | `/api/usuario/{id}` | Obtener usuario por ID |
| POST | `/api/post` | Crear post |
| GET | `/api/post/{id}` | Obtener post por ID |
| GET | `/api/posts` | Listar posts (paginado) |
| GET | `/api/post?titulo=x` | Buscar por título |
| GET | `/api/posts/recientes` | Top 5 posts recientes |
| POST | `/api/comentario` | Crear comentario |
| GET | `/api/comentario/{id}` | Obtener comentario por ID |

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
    database-platform: org.hibernate.dialect.MariaDBDialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

**Variables de entorno requeridas**:
- `DB_URL` - JDBC URL
- `DB_USERNAME` - Usuario de MariaDB
- `DB_PASSWORD` - Contraseña de MariaDB

---

## Notas Importantes

1. **ddl-auto: update** - Hibernate crea/actualiza tablas automáticamente
2. **FetchType.LAZY** - Las relaciones se cargan bajo demanda
3. **Constructor vacío** - Las entidades JPA lo requieren
4. **Mapper null-check** - Siempre verificar null antes de mapear
5. **RequestDTO usa IDs** - Para relaciones, recibir `Long <nombre>Id`
6. **ResponseDTO usa DTOs** - Para relaciones, devolver DTOs anidados
7. **Consultas complejas** - Usar `@Query` con `JOIN FETCH` para evitar N+1
8. **@Transactional** - Usar en servicios para mantener sesión abierta
9. **Excepciones** - Usar personalizadas para errores de negocio
