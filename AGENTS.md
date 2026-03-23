# AGENTS.md - Code Guidelines

## Repository Overview

**Java 21** practice repository with Spring Boot projects in `Nivel_12/`.

| Project | Spring Boot | Description |
|---------|-------------|-------------|
| `Nivel_12/proyecto01/` | 3.2.5 | Configuration API with profiles |
| `Nivel_12/proyecto02/` | 4.0.4 | External API client with config |

## Build Commands

```bash
cd Nivel_12/proyecto01  # or proyecto02

mvn clean compile                    # Compile only
mvn test                            # Run all tests
mvn test -Dtest=AppTest             # Run single test class
mvn test -Dtest=AppTest#methodName  # Run single test method
mvn test -Dspring.profiles.active=dev
mvn package                         # Build JAR
mvn spring-boot:run                 # Run application
mvn dependency:tree                 # View dependencies
```

## Code Style

- **Java**: 21+ | **Indentation**: 4 spaces | **Encoding**: UTF-8
- **Braces**: K&R style (same line) | **Line length**: <120 chars

### Naming Conventions
| Element | Convention | Example |
|---------|------------|---------|
| Classes | PascalCase | `AppConfig` |
| Methods | camelCase | `getUserById()` |
| Variables | camelCase | `userName` |
| Constants | UPPER_SNAKE | `MAX_RETRIES` |
| Packages | lowercase | `com.achiridev` |
| YAML keys | kebab-case | `api-clima` |

### Import Order
```java
import java.time.*;
import org.springframework.*;
import com.achiridev.*;
```

### Class Structure
1. Package → 2. Imports → 3. Annotations → 4. Class declaration
5. Fields (private) → 6. Constructors → 7. Public methods
8. Private methods → 9. Getters/Setters

## Code Examples

**Controller**:
```java
@RestController
@RequestMapping("/api")
public class ClimaController {
    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping("/clima")
    public Clima clima() {
        return climaService.obtenerClima();
    }
}
```

**Configuration Properties**:
```java
@Component
@ConfigurationProperties(prefix = "api.clima")
public class Clima {
    private String url;
    private int timeout;

    public Clima() { }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public int getTimeout() { return timeout; }
    public void setTimeout(int timeout) { this.timeout = timeout; }
}
```

**Service**:
```java
@Service
public class InfoService {
    private final AppConfig appConfig;

    public InfoService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}
```

## Spring Annotations

| Annotation | Purpose |
|------------|---------|
| `@SpringBootApplication` | Main application class |
| `@Component` | Generic Spring bean |
| `@Service` | Service layer |
| `@RestController` | REST API controller |
| `@Configuration` | Configuration class |
| `@ConfigurationProperties` | Type-safe config binding |
| `@GetMapping`, `@PostMapping`, etc. | HTTP mappings |
| `@Value` | Simple property injection |

## Configuration Files

**Location**: `src/main/resources/`
- `application.yml` - Default configuration
- `application-{profile}.yml` - Profile overrides (dev, prod)

```yaml
mi:
  app:
    nombre: "Sistema"
    version: "1.0.0"
api:
  clima:
    timeout: 3000
```

## Testing

**Unit Test**:
```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
```

**Integration Test**:
```java
@SpringBootTest
class Proyecto02ApplicationTests {
    @Test
    void contextLoads() { }
}
```

## Error Handling
- Use try-catch for recoverable errors
- Let unchecked exceptions propagate
- Use `System.out.println()` for logging
- Consider `@ControllerAdvice` for API exceptions

## Important Notes
1. Always run `mvn test` before commits
2. Use constructor injection (avoid field injection)
3. Follow JavaBean conventions for config properties
4. Prefer `@ConfigurationProperties` over `@Value` for structured config
5. Profile-specific configs via `spring.profiles.active`

## Directory Structure
```
Nivel_12/
├── proyecto01/
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/achiridev/
│       │   ├── App.java, config/, controller/, service/
│       └── resources/
│           ├── application.yml, application-dev.yml, application-prod.yml
│       └── test/java/com/achiridev/
└── proyecto02/
    └── src/main/java/com/achiridev/, resources/
```
