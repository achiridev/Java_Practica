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
- Clases: PascalCase (`Producto`, `UsuarioMapper`)
- DTOs: PascalCase + suffix `*DTO` (`ProductoCreateDTO`, `UsuarioResponseDTO`)
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
│   ├── SecurityConfig.java          (Seguridad con roles y permisos)
│   ├── DataInitializer.java         (Inicializa roles y permisos en BD)
│   └── RolRepository.java, PermisoRepository.java
├── controller/
│   └── ProductoController.java      (REST API de productos)
├── dto/
│   ├── ProductoCreateDTO.java       (Input producto)
│   ├── ProductoResponseDTO.java     (Output producto completo)
│   ├── ProductoResumeDTO.java       (Output producto resumido)
│   ├── UsuarioCreateDTO.java        (Input registro usuario)
│   ├── UsuarioLoginDTO.java         (Input login)
│   └── UsuarioResponseDTO.java       (Output usuario)
├── exception/
│   ├── RecursoNoEncontradoException.java
│   └── RecursoYaExisteException.java
├── extra/
│   └── PageResponse.java            (Wrapper paginación)
├── mapper/
│   ├── ProductoMapper.java
│   └── UsuarioMapper.java
├── model/
│   ├── Producto.java                (Entidad JPA, BigDecimal precio)
│   ├── Usuario.java                 (@Id @GeneratedValue, ManyToMany roles)
│   ├── Rol.java                     (ManyToMany permisos)
│   └── Permiso.java
├── repository/
│   ├── ProductoRepository.java      (findByNombre, existsByNombre)
│   ├── UsuarioRepository.java       (findByEmail, existsByEmail)
│   ├── RolRepository.java           (findByNombre)
│   └── PermisoRepository.java
├── security/
│   ├── jwt/
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── JwtService.java
│   │   └── JwtConfig.java
│   ├── user/
│   │   └── CustomUserDetailsService.java
│   └── auth/
│       └── AuthService.java         (login, registrarUsuario)
└── service/
    └── ProductoService.java
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

- Entidades usan `@Entity`, `@Table`, `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)`
- BigDecimal: `@Column(precision=10, scale=2)`
- DTOs usan anotaciones Jakarta: `@NotBlank`, `@NotNull`, `@Positive`, `@Size`, `@Email`
- Repository extiende `JpaRepository<Entity, Long>`
- Mapper con null-check en todos los métodos
- Excepciones personalizadas extienden `RuntimeException`
- Autenticación JWT con `JwtAuthenticationFilter`, `JwtService`, `JwtConfig`
- Paginación: servicio retorna `Page<Entity>`, controller usa `PageResponse`
- SecurityConfig: permisos con `hasAuthority()` y roles con `hasRole()`
- CustomUserDetailsService carga roles y permisos como `GrantedAuthority`
- DataInitializer crea permisos (READ_PRODUCT, CREATE_PRODUCT, UPDATE_PRODUCT, DELETE_PRODUCT) y roles (ADMIN, MODERATOR, USER)