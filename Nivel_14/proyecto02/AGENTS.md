# AGENTS.md - Proyecto 02 (Nivel 14)

## Comandos Maven (mvn)

### Compilación y Ejecución
```bash
./mvnw compile              # Compila el proyecto
./mvnw package -DskipTests  # Genera JAR sin ejecutar tests
./mvnw spring-boot:run      # Ejecuta la aplicación
./mvnw clean                # Limpia target/
./mvnw clean package        # Build completo
```

### Pruebas
```bash
./mvnw test                 # Ejecuta todos los tests
./mvnw test -Dtest=Proyecto02ApplicationTests           # Una clase de test
./mvnw test -Dtest=Proyecto02ApplicationTests#contextLoads  # Un método específico
```

## Guías de Estilo de Código Java

### Imports (orden estricto)
1. `java.*`
2. `jakarta.*` (validation)
3. `org.springframework.*`
4. `com.achiridev.*`

### Convenciones de Nombres
| Elemento | Formato | Ejemplo |
|----------|---------|---------|
| Clases | PascalCase | `PedidoService`, `ErrorResponse` |
| DTOs Request | PascalCase + Suffix | `CrearPedidoRequestDTO` |
| DTOs Response | PascalCase + Suffix | `PedidoResponseDTO`, `PedidoResumeDTO` |
| Métodos | camelCase | `crearPedido`, `buscarPorCliente` |
| Variables | camelCase | `pedidoService`, `dto` |
| Entidades JPA | PascalCase | `Pedido`, `Cliente`, `Producto` |
| Tablas DB | minúsculas | `@Table(name = "pedidos")` |

### Estructura de Clases
1. Package declaration
2. Imports (agrupados)
3. Anotaciones de clase (`@Entity`, `@Service`, `@RestController`)
4. Declaration de clase
5. Campos privados (final si son inyección)
6. Constructor
7. Métodos públicos
8. Métodos privados
9. Getters/Setters

### Patrón de Capas
- **Controller**: `@RestController` + `@RequestMapping("/api")`, retorna `ResponseEntity<?>`.
- **Service**: `@Service`, lógica de negocio, usa `@Transactional`, inyección por constructor.
- **Repository**: extiende `JpaRepository<Entity, Long>`, usa `Page` y `Optional`.
- **DTOs**: POJOs con getters/setters, validación con `@Valid` + anotaciones Jakarta.
- **Mapper**: `@Component`, métodos `toEntity()`, `toDTO()`, null-check obligatorio.

### Manejo de Excepciones
- Usar `RecursoNoEncontradoException` para 404.
- Usar `IllegalArgumentException` para 400 (validaciones de negocio).
- `GlobalExceptionHandler` con `@RestControllerAdvice` para manejar globalmente.
- `ErrorResponse` con campos: status, message, timestamp.

### Buenos Prácticas
- Inyección por constructor (nunca `@Autowired` en campos).
- DTOs separados para Request y Response.
- Validación en DTOs con `@NotBlank`, `@NotNull`, `@Min`, `@Max`, `@Email`.
- Nombres de métodos en español en Service, inglés en Controller.
- Usar `Optional` en repository para búsquedas por ID.
- Paginación con `Pageable` y `Page<T>`.
- Transacciones con `@Transactional` en métodos de escritura.

## Estructura del Proyecto

```
src/main/java/com/achiridev/
├── Proyecto02Application.java       # Main class
├── controller/                       # REST endpoints
│   ├── PedidoController.java        # /api/pedido, /api/pedidos
│   ├── ClienteController.java
│   └── ProductoController.java
├── service/                          # Lógica de negocio
│   ├── PedidoService.java
│   ├── ClienteService.java
│   ├── ProductoService.java
│   └── Response/
│       └── PageResponse.java         # Wrapper para paginación
├── repository/                       # Acceso a datos (JPA)
│   ├── PedidoRepository.java
│   ├── ClienteRepository.java
│   ├── ProductoRepository.java
│   └── DetallePedidoRepository.java
├── model/                            # Entidades JPA
│   ├── Pedido.java
│   ├── Cliente.java
│   ├── Producto.java
│   └── DetallePedido.java
├── dto/                              # Data Transfer Objects
│   ├── Pedido/
│   │   ├── CrearPedidoRequestDTO.java
│   │   ├── PedidoResponseDTO.java
│   │   └── PedidoResumeDTO.java
│   ├── Cliente/
│   │   ├── CrearClienteRequestDTO.java
│   │   └── ClienteResponseDTO.java
│   ├── Producto/
│   │   ├── CrearProductoRequestDTO.java
│   │   └── ProductoResponseDTO.java
│   └── DetallePedido/
│       ├── CrearDetallePedidoRequestDTO.java
│       └── DetallePedidoResponseDTO.java
├── mapper/                           # Conversión Entity <-> DTO
│   ├── PedidoMapper.java
│   ├── ClienteMapper.java
│   ├── ProductoMapper.java
│   └── DetallePedidoMapper.java
└── exception/                       # Manejo de errores
    ├── GlobalExceptionHandler.java
    ├── RecursoNoEncontradoException.java
    └── ErrorResponse.java
```

## Dependencias Clave del pom.xml

```xml
<java.version>21</java.version>

<!-- Spring Boot Starters -->
spring-boot-starter-webmvc       # REST API
spring-boot-starter-data-jpa    # JPA/Hibernate
spring-boot-starter-validation  # Jakarta Validation

<!-- Base de datos -->
mariadb-java-client              # Driver MariaDB

<!-- Test -->
spring-boot-starter-test         # JUnit 5, Mockito
```

### Versiones (del parent)
- Spring Boot: **4.0.5**
- Java: **21**
- JUnit 5 (vía starter test)

## Notas Importantes

1. **No usar Lombok** - este proyecto usa getters/setters explícitos.
2. **Sin Swagger** - no hay dependencias de documentación API.
3. **Sin Spring Security** - proyecto sin autenticación.
4. **BD MariaDB** - configurar en `application.properties` o `application.yml`.
5. **Paginación** - usar `Page<ResponseDTO>` y envolver en `PageResponse`.
6. **Test único** - `./mvnw test -Dtest=ClaseTest#metodo`.
