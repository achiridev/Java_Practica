# Spring Core

## 🚀 PROYECTO 1 — Sistema de Conversión de Archivos JSON

### 📌 Caso real

Una empresa tiene un sistema que recibe **JSON de usuarios** y necesita **convertirlos a Objetos Java** para enviarlos a otro microservicio.

El sistema debe usar **Jackson**, pero el `ObjectMapper` debe ser configurado como **Bean de Spring**, ya que es una librería externa.

### 🧩 Requisitos

Debes crear las siguientes capas:

controller  
service  
repository  
config  
model

#### 1. Modelo

Usuario

- id  
- nombre  
- email

#### 2. Repository

Simula guardar usuarios.

```java
@Repository  
public class UsuarioRepository {  
    public void guardar(Usuario usuario) {  
        System.out.println("Usuario guardado");  
    }  
}
```

#### 3. Service

Debe realizar validaciones y llamar al Repository.

#### 4. Controller

Debe:

- recibir un JSON
- convertirlo a `Usuario`
- enviarlo al service

Usa `ObjectMapper`.

#### 5. Configuración

Debes crear:

```java
@Configuration  
public class JacksonConfig
```

Y definir un Bean:

```java
@Bean  
ObjectMapper objectMapper()
```

## 🛠️ Condiciones

Debes usar obligatoriamente:

✔ `@Service`  
✔ `@Repository`  
✔ `@Component` o `@Controller`  
✔ `@Configuration`  
✔ `@Bean`

La inyección debe ser **por constructor**.

## 🧠 Aprendes

Con este ejercicio practicas:

- IoC
- DI por constructor
- Beans
- Configuración con `@Bean`
- Integración con librerías externas
- Arquitectura por capas con Spring

---

## 🚀 PROYECTO 2 — Sistema de Tareas Concurrentes

### 📌 Caso real

Un sistema de procesamiento de trabajos debe crear **nuevas tareas dinámicamente**.

Cada tarea debe ser **un objeto independiente**, porque puede ejecutarse en paralelo.

Para esto usarás **Scope prototype**.

### 🧩 Requisitos

Debes crear:

- task  
- service  
- config

#### 1. Clase Task

Debe tener:

```css
id  
descripcion
```

Debe imprimir cuando se crea:

```java
System.out.println("Nueva tarea creada");
```

Y debe tener:

```java
@Scope("prototype")
```

#### 2. TaskService

Debe crear tareas dinámicamente.

Ejemplo:

```java
public void ejecutarTareas()
```

Debe crear **3 tareas distintas**.

#### 3. Lifecycle

La clase Task debe usar:

```java
@PostConstruct  
@PreDestroy
```

Para mostrar:

```css
Tarea inicializada  
Tarea destruida
```

#### 4. Simulación

Crea una clase `Main` que obtenga el servicio desde `ApplicationContext`.

### 🛠️ Condiciones

Debes usar:

✔ `@Component`  
✔ `@Scope("prototype")`  
✔ `@PostConstruct`  
✔ `@PreDestroy`  
✔ `ApplicationContext`

### 🧠 Aprendes

Este ejercicio te enseña:

- Scopes
- Prototype vs Singleton
- Lifecycle de Beans
- Creación dinámica de objetos
- Cómo Spring gestiona memoria

## 🚀 PROYECTO 3 — Sistema de Notificaciones (Email/SMS)

### 📌 Caso real

Una aplicación necesita enviar **notificaciones a usuarios**.

Pero el canal puede cambiar:

- Email  
- SMS  
- Push

Para esto se usa **inyección de dependencias y Beans configurados manualmente**.

### 🧩 Requisitos

Debes crear una arquitectura así:

notification  
service  
config

#### 1. Interfaz NotificationSender

```java
public interface NotificationSender {  
    void enviar(String mensaje);  
}
```

#### 2. Implementación Email

```java
@Component  
public class EmailSender implements NotificationSender {  
}
```

#### 3. Implementación SMS

```java
@Component  
public class SmsSender implements NotificationSender {  
}
```

#### 4. NotificationService

Debe recibir un `NotificationSender`.

```java
@Service  
public class NotificationService {  
}
```

Y enviar notificaciones.

#### 5. Configuración

Debes crear una clase:

```java
@Configuration  
public class NotificationConfig
```

Y elegir cuál implementación usar:

```java
@Bean  
public NotificationSender notificationSender() {  
    return new EmailSender();  
}
```

### 🛠️ Condiciones

Debes usar:

✔ `@Component`  
✔ `@Service`  
✔ `@Configuration`  
✔ `@Bean`  
✔ Inyección por constructor

No se permite usar `@Autowired` en campos.

## 🧠 Aprendes

Este ejercicio te enseña algo **muy importante en sistemas reales**:

- desacoplamiento con interfaces
- selección de implementación con Spring
- configuración manual de Beans
- principios SOLID (DIP)
