# AGENTS.md - Proyecto02

## 1. Comandos Maven

```bash
./mvnw compile
./mvnw test
./mvnw test -Dtest=Proyecto02ApplicationTests
./mvnw test -Dtest=Proyecto02ApplicationTests#nombreMetodo
./mvnw package -DskipTests
./mvnw spring-boot:run
./mvnw clean
./mvnw clean package
```

## 2. Guías de Estilo Java

### Imports
Orden exacto:
1. `java.*` + `java.math.*` (BigDecimal)
2. `jakarta.*` (JPA, validation)
3. `org.springframework.*`
4. `com.achiridev.*`

### Convenciones de Nombres
- Clases: PascalCase (`Producto`, `ProductoMapper`)
- DTOs: PascalCase + suffix `*DTO` (`ProductoCreateDTO`, `ProductoResponseDTO`, `ProductoResumeDTO`)
- Métodos: camelCase (`toEntity`, `toResponseDTO`, `findById`, `findByNombre`, `save`, `update`, `deleteById`)
- Variables: camelCase (`dto`, `producto`, `productoExistente`)
- Excepciones: PascalCase + suffix `*Exception` (`RecursoNoEncontradoException`)

### Mapper (patrón)
- Anotar con `@Component`
- Métodos: `toEntity()` y `toResponseDTO()`
- Siempre validar null antes de mapear

### Service
- Anotar con `@Service`
- Usar `@Transactional` en métodos que modifican datos (`save`, `update`, `deleteById`)
- Validaciones de negocio: verificar existencia de recursos con repository (`existsByNombre`)
- No duplicar validaciones de Jakarta Validator (ya están en DTOs)

### Controller
- Usar `@RestController` con `@RequestMapping` a nivel de clase
- Métodos en inglés: `create`, `getById`, `getAll`, `update`, `delete`
- Retornar `ResponseEntity<?>` con códigos HTTP apropiados
- Usar `@Valid` en DTOs de entrada

## 3. Estructura del Proyecto

```
src/main/java/com/achiridev/
├── Proyecto02Application.java       (Main Spring Boot)
├── config/
│   └── SecurityConfig.java          (Configuración de seguridad)
├── controller/
│   └── ProductoController.java      (REST API de productos)
├── dto/
│   ├── ProductoCreateDTO.java       (Input con @NotBlank, @NotNull, @Positive)
│   ├── ProductoResponseDTO.java     (Output completo)
│   └── ProductoResumeDTO.java       (Output resumido)
├── exception/
│   ├── RecursoNoEncontradoException.java
│   └── RecursoYaExisteException.java
├── extra/
│   └── PageResponse.java           (Wrapper para paginación)
├── mapper/
│   └── ProductoMapper.java          (Conversión entity<->DTO)
├── model/
│   ├── Producto.java               (Entidad JPA, BigDecimal precio)
│   ├── Usuario.java
│   ├── Rol.java
│   └── Permiso.java
├── repository/
│   ├── ProductoRepository.java     (JpaRepository, findByNombre, existsByNombre)
│   └── UsuarioRepository.java      (findByEmail, existsByEmail)
├── security/
│   ├── jwt/
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── JwtService.java
│   │   └── JwtConfig.java
│   └── user/
│       └── CustomUserDetailsService.java
└── service/
    └── ProductoService.java         (CRUD: findById, findByNombre, save, update, deleteById)
```

## 4. Dependencias Clave (pom.xml)

- **Spring Boot**: 4.0.5
- **Java**: 21
- **spring-boot-starter-data-jpa**: Persistencia ORM
- **spring-boot-starter-security**: Seguridad con JWT
- **spring-boot-starter-validation**: Validación Jakarta
- **spring-boot-starter-webmvc**: REST API
- **mariadb-java-client**: Base de datos MariaDB
- **jjwt-api/impl/jackson 0.12.5**: JWT tokens

## 5. Notas Importantes

- Entidades usan `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column(precision=10, scale=2)` para BigDecimal
- DTOs usan anotaciones Jakarta: `@NotBlank`, `@NotNull`, `@Positive`, `@Size`
- Repository extiende `JpaRepository<Entity, Long>` con métodos: `findByNombre`, `existsByNombre`
- Mapper con null-check en todos los métodos
- Excepciones personalizadas en paquete `exception`, extends `RuntimeException`
- Usar `@RestControllerAdvice` para manejo global de excepciones
- Precio en Producto, DTOs y Mapper es `BigDecimal` (no Double)
- Autenticación JWT con `JwtAuthenticationFilter`, `JwtService` y `JwtConfig`
- Paginación: servicio retorna `Page<Entity>`, controller usa `PageResponse` como wrapper
- SecurityConfig configura `SecurityFilterChain` con `JWTFilter` y `ProviderManager`