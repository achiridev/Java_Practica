# AGENTS.md - Proyecto Nivel 15

## Comandos Maven

```bash
# Compilar proyecto
./mvnw compile

# Ejecutar todos los tests
./mvnw test

# Ejecutar una clase de test específica
./mvnw test -Dtest=Proyecto01ApplicationTests

# Ejecutar un método de test específico
./mvnw test -Dtest=Proyecto01ApplicationTests#nombreMetodo

# Empaquetar (sin tests)
./mvnw package -DskipTests

# Ejecutar la aplicación
./mvnw spring-boot:run

# Compilación limpia
./mvnw clean compile
```

## Dependencias Clave del pom.xml

- **Spring Boot 4.0.5** con Java 21
- `spring-boot-starter-data-jpa` - Persistencia JPA/Hibernate
- `spring-boot-starter-security` - Seguridad y autenticación
- `spring-boot-starter-validation` - Validación con Jakarta
- `spring-boot-starter-webmvc` - REST controllers
- `mariadb-java-client` - Driver MariaDB (runtime)
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` (0.12.5) - JWT tokens

## Estructura del Proyecto

```
src/main/java/com/achiridev/
├── Proyecto01Application.java      # Main class
├── user/                           # Módulo de usuarios
│   ├── controller/UsuarioController.java
│   ├── service/UsuarioService.java
│   ├── repository/UsuarioRepository.java
│   ├── model/Usuario.java, Rol.java
│   ├── dto/UsuarioCreateDTO, UsuarioResponseDTO, UsuarioAutorizadoDTO, UsuarioLoginDTO
│   └── mapper/UsuarioMapper.java
├── auth/                           # Autenticación
│   ├── controller/AuthController.java
│   ├── service/AuthService.java
│   └── controller/AuthResponse.java
├── security/                       # Seguridad JWT
│   ├── service/CustomUserDetailsService.java
│   ├── jwt/JwtService.java, JwtAuthenticationFilter.java, JwtConfig.java
├── config/                         # Configuración
│   ├── SecurityConfig.java
│   └── DataInitializer.java
├── exception/                      # Manejo de errores
│   ├── GlobalExceptionHandler.java
│   ├── ErrorResponse.java
│   └── RecursoNoEncontradoException.java
└── publico/
    └── EndpointsPublicosController.java
```

**Arquitectura**: Controller -> Service -> Repository (inyección por constructor)

## Guías de Estilo de Código

### Imports
Orden obligatorio:
1. `java.*`
2. `jakarta.*` (validation)
3. `org.springframework.*`
4. `com.achiridev.*`

### Convenciones de Nombres
| Elemento | Formato | Ejemplo |
|----------|---------|---------|
| Paquetes | lowercase | `com.achiridev.user` |
| Clases | PascalCase | `UsuarioController`, `UsuarioService` |
| DTOs | PascalCase + Sufijo | `UsuarioCreateDTO`, `UsuarioResponseDTO` |
| Métodos | camelCase | `getPerfil()`, `registrarUsuario()` |
| Variables | camelCase | `usuarioRepository`, `entity` |
| Constantes | UPPER_SNAKE_CASE | `MAX_INTENTOS` |

### Patrones Obligatorios
- **Inyección**: Constructor injection (nunca field injection)
- **DTOs**: Retornar DTOs, NO entidades en controllers/services
- **Validación**: Anotaciones Jakarta (`@NotBlank`, `@Email`, `@Size`) en DTOs request
- **Errores**: Usar `@RestControllerAdvice` + excepciones personalizadas
- **Mappers**: `@Component` con métodos `toEntity()` / `toDTO()`
- **Repository**: Retornar `Optional<T>` para resultados opcionales

### Manejo de Excepciones
- `RecursoNoEncontradoException` para 404
- `IllegalArgumentException` para 400
- `BadCredentialsException` para 401 (handled en GlobalExceptionHandler)
- Mensajes de error en español

### Formato
- 4 espacios (no tabs)
- Llave apertura en misma línea
- Líneas bajo 120 caracteres cuando sea práctico
- Comentar en español para mensajes al usuario, inglés para código

## Errores Corregidos (para referencia)

1. **UsuarioMapper sin @Component**: El mapper no era detectado como bean. Solución: agregar `@Component` a la clase.

2. **SecurityConfig sin AuthenticationManager**: Faltaba el bean `AuthenticationManager`. Solución: crear bean con `ProviderManager` y `DaoAuthenticationProvider` que reciba `CustomUserDetailsService`.

3. **UsuarioService sin @Service**: La clase de servicio no tenía la anotación. Solución: agregar `@Service`.

4. **Rol con ID manual**: El entity `Rol` usaba `@Id` sin `@GeneratedValue`. Solución: usar `@GeneratedValue(strategy = GenerationType.SEQUENCE)` con `@SequenceGenerator`.

5. **DataInitializer nombres incorrectos**: Se usaba "USER" pero el código buscaba "ROLE_USER". Solución: cambiar a "ROLE_USER" y "ROLE_ADMIN".

## Notas del Proyecto

### Endpoints
- `/api/auth/register` - Registro de usuario (público)
- `/api/auth/login` - Login que retorna token JWT (público)
- `/api/user/perfil` - Perfil del usuario autenticado (requiere JWT con rol USER o ADMIN)
- `/api/public/**` - Endpoints públicos

### JWT Flow
1. Usuario hace login con email/password
2. `AuthService.login()` autentica con `AuthenticationManager`
3. `JwtService.generateToken()` crea token con email y roles
4. Cliente incluye token en header `Authorization: Bearer <token>`
5. `JwtAuthenticationFilter` valida token y establece autenticación en SecurityContext
6. Endpoints protegidos verifican que el usuario tenga rol correcto

### Configuración Requerida (application.yaml)
```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update

jwt:
  secret: ${CLAVE_JWT}
  expiration: 86400000
```
**Importante**: La clave JWT debe tener al menos 32 bytes (256 bits) para HS256.

### Seguridad
- CSRF disabled en SecurityConfig
- CORS vacío (configuración básica)
- Passwords encriptadas con BCrypt
- JWT con filtro antes de UsernamePasswordAuthenticationFilter
- Roles: `ROLE_USER`, `ROLE_ADMIN`