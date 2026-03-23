# AGENTS.md - Code Guidelines for Agents

This document provides guidelines for agents working in this Spring Boot repository.

## Project Overview

This is a **Spring Boot 3.2.5** application using **Java 21** and **Maven** for build management. The project is located in `Nivel_12/proyecto01/` and demonstrates configuration API with profiles.

## Build Commands

### Compile, Test, Package
```bash
cd Nivel_12/proyecto01

mvn clean compile                    # Compile only
mvn test                            # Run all tests
mvn test -Dtest=AppTest             # Run single test class
mvn test -Dtest=AppTest#methodName  # Run single test method
mvn test -Dtest="*Test"             # Run all tests matching pattern
mvn test -Dspring.profiles.active=dev    # Test with specific profile
mvn package                         # Build JAR (skip tests)
mvn package -DskipTests             # Skip tests during packaging
mvn spring-boot:run                 # Run Spring Boot application
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
mvn clean package                   # Clean and full build
```

### Other Useful Commands
```bash
mvn dependency:tree                  # View dependency tree
mvn dependency:analyze              # Analyze unused dependencies
mvn validate                        # Validate project structure
```

## Code Style Guidelines

### General Conventions
- **Language**: Java 21 (minimum)
- **Encoding**: UTF-8
- **Indentation**: 4 spaces (no tabs)
- **Line Length**: Under 120 characters when practical
- **Braces**: Opening brace on same line, closing on new line

### Naming Conventions
| Element       | Convention       | Example                    |
|---------------|------------------|----------------------------|
| Classes       | PascalCase       | `InfoService`, `AppConfig` |
| Interfaces    | PascalCase       | `UserRepository`           |
| Methods       | camelCase        | `getUserById()`, `obtenerInfo()` |
| Variables     | camelCase        | `userName`, `isActive`     |
| Constants     | UPPER_SNAKE_CASE | `MAX_RETRY_COUNT`          |
| Packages      | lowercase        | `com.achiridev`            |
| YAML keys     | kebab-case       | `spring-application`       |

### Import Organization
Group imports in this order with blank lines between groups:
1. `java.*` imports
2. Third-party imports (`org.*`, `com.*`, etc.)
3. Project imports (`com.achiridev.*`)

### Class Structure
1. Package declaration
2. Import statements (grouped, alphabetically within groups)
3. Class-level annotations
4. Class declaration
5. Fields (private first)
6. Constructors
7. Public methods
8. Private methods
9. Getters/Setters (grouped together)

### Annotation Usage
- `@SpringBootApplication` - main application class
- `@Component` - generic Spring-managed beans
- `@Service` - service layer classes
- `@Repository` - data access classes
- `@RestController` - REST API controllers
- `@ConfigurationProperties(prefix = "...")` with `@Component` - configuration binding
- Place annotations on separate lines before class/method declarations

### Spring Boot Patterns

```java
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@Component
@ConfigurationProperties(prefix = "mi.app")
public class AppConfig {
    private String nombre;
    private String version;
    private String entorno;
    private boolean debug;

    public AppConfig() { }  // Required for Spring binding

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    // ... other getters/setters
}

@Service
public class InfoService {
    private final AppConfig appConfig;

    public InfoService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public AppConfig getInfo() {
        return appConfig;
    }
}

@RestController
@RequestMapping("/info")
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public AppConfig getInfo() {
        return infoService.getInfo();
    }
}
```

### Configuration Files
- Location: `src/main/resources/`
- Use `application.yml` for YAML configuration
- Profile-specific: `application-dev.yml`, `application-prod.yml`
- Property prefix must match `@ConfigurationProperties(prefix = "...")`

```yaml
# application.yml
mi:
  app:
    nombre: "Sistema de Ventas"
    version: "1.0.0"

# application-dev.yml
mi:
  app:
    entorno: "DEV"
    debug: true

# application-prod.yml
mi:
  app:
    entorno: "PROD"
    debug: false
```

### Testing Conventions
```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
```

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppIntegrationTest {
    @Test
    void contextLoads() {
    }
}
```

- Test classes: `*Test.java` in `src/test/java/`
- Use JUnit 5 (`org.junit.jupiter.api.Test`)
- Use `assertTrue`, `assertEquals`, etc. from `org.junit.jupiter.api.Assertions`
- Use `@SpringBootTest` for integration tests

### Error Handling
- Use try-catch for expected, recoverable exceptions
- Let unchecked exceptions propagate for unexpected errors
- Log errors using appropriate logging framework
- Provide meaningful error messages in exceptions

### Documentation
- Use Javadoc for public APIs
- Inline comments should explain "why", not "what"
- Keep comments up-to-date with code changes

## Directory Structure
```
proyecto01/
├── pom.xml
├── .mvn/
│   ├── jvm.config
│   └── maven.config
└── src/
    ├── main/
    │   ├── java/com/achiridev/
    │   │   ├── App.java
    │   │   ├── config/
    │   │   │   └── AppConfig.java
    │   │   ├── controller/
    │   │   │   └── InfoController.java
    │   │   └── service/
    │   │       └── InfoService.java
    │   └── resources/
    │       ├── application.yml
    │       ├── application-dev.yml
    │       └── application-prod.yml
    └── test/
        └── java/com/achiridev/
            └── AppTest.java
```

## Dependencies
- **Spring Boot Starter Web**: `org.springframework.boot:spring-boot-starter-web`
- **JUnit 5**: `org.junit.jupiter:junit-jupiter-api` (test scope)
- **JUnit 5 Params**: `org.junit.jupiter:junit-jupiter-params` (test scope)

## Important Notes
1. Always run `mvn test` before committing changes
2. Ensure compilation succeeds with `mvn compile`
3. Use constructor injection - avoid field injection
4. Follow JavaBean conventions for properties (getters/setters)
5. Profile-specific configurations are loaded based on `spring.profiles.active`
6. Prefer `@ConfigurationProperties` over `@Value` for structured configuration
