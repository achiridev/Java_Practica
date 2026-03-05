# Libreria Jackson

## 🚀 PROYECTO 1 — Sistema de Órdenes con Historial y Fechas

### 📌 Caso real

Estás desarrollando un sistema de e-commerce.  
Una **Orden** contiene:

- id
- cliente
- fechaCreacion (`LocalDateTime`)
- lista de productos
- total
- estado
- notas internas (no deben ir al JSON)

El frontend envía y recibe JSON.

### 🧩 Requisitos

Debes:

1. Agregar Jackson en Maven (`jackson-databind` + `jackson-datatype-jsr310`).
2. Crear las clases:
    - `Producto`
    - `Orden`
3. Usar:
    - `@JsonProperty`
    - `@JsonIgnore`
4. Registrar `JavaTimeModule`
5. Desactivar timestamps
6. Serializar:
    - Una orden con 3 productos
7. Deserializar:
    - Un JSON recibido del frontend
8. Usar `Pretty Print`
9. Configurar:
    - No incluir campos null

### 🛠️ Condiciones

#### 🔹 Producto

- nombre
- precio
- cantidad

#### 🔹 Orden

- id
- cliente
- fechaCreacion → `LocalDateTime`
- productos → `List<Producto>`
- total
- estado
- notasInternas → `@JsonIgnore`

#### 🔹 Extra dificultad

- El JSON del frontend usa:
    - `"order_id"` en vez de `id`
    - `"created_at"` en vez de `fechaCreacion`

Debes usar `@JsonProperty`.

- Si el JSON viene mal formado:
    - Capturar `JsonProcessingException`
    - Mostrar mensaje personalizado.

### 🧠 Aprendes

- Jackson + Maven
- Listas dentro de objetos
- Manejo correcto de fechas modernas
- Pretty print
- Ignorar campos sensibles
- Mapear nombres diferentes
- Manejo real de errores de parsing

## 🚀 PROYECTO 2 — Importador Masivo de Usuarios (Lista + Validación + Errores)

### 📌 Caso real

Tu backend recibe un archivo JSON con una lista masiva de usuarios:

```json
[  
  {  
    "user_name": "Daniel",  
    "age": 25,  
    "email": "daniel@email.com",  
    "birth_date": "2000-05-10"  
  }  
]
```

Debes convertirlo en `List<Usuario>`.

### 🧩 Requisitos

1. Crear clase `Usuario`:
    - nombre
    - edad
    - email
    - fechaNacimiento (`LocalDate`)
    - password (ignorado en JSON)
2. Usar:
    - `@JsonProperty`
    - `@JsonIgnore`
3. Registrar módulo de fechas.
4. Deserializar correctamente usando:

```java
new TypeReference<List<Usuario>>() {}
```

5. Si:
    - Falta el campo `"user_name"`
    - O la fecha es inválida

Debes capturar la excepción y:

- Mostrar error
- No detener el programa

6. Serializar nuevamente la lista con:
    - Pretty Print
    - Excluir null
    - Formato ISO estándar

### 🛠️ Condiciones

- Intentar primero hacer mal:
    ```java
    mapper.readValue(json, List.class); // Debe fallar conceptualmente
    ```
- Luego hacerlo bien con `TypeReference`.
- Agregar al menos:
    - 3 usuarios válidos
    - 1 usuario con error de formato

### 🧠 Aprendes

- Por qué existe `TypeReference`
- Type Erasure
- Manejo real de listas
- Manejo de errores en importaciones
- Fechas en JSON
- Reglas de validación simples
- Buenas prácticas backend reales

## 🚀 PROYECTO 3 — Sistema de Eventos con Configuración Global del ObjectMapper

### 📌 Caso real

Vas a construir un micro-servicio que maneja eventos del sistema:

Ejemplo JSON esperado:

```json
{  
  "event_id": 1001,  
  "name": "Login",  
  "timestamp": "2026-03-04T15:30:00",  
  "metadata": {  
    "ip": "192.168.1.10",  
    "device": "mobile"  
  }  
}
```

### 🧩 Requisitos

1. Crear clase:
    - `Evento`
    - `Metadata`
2. Configurar globalmente el `ObjectMapper`:
    - Registrar `JavaTimeModule`
    - Desactivar timestamps
    - `NON_NULL`
    - Pretty Print por defecto
3. Usar:
    - `@JsonProperty("event_id")`
    - `@JsonProperty("timestamp")`
4. `timestamp` debe ser `LocalDateTime`.
5. Serializar múltiples eventos (`List<Evento>`).
6. Deserializar un JSON con:
    - Un campo adicional desconocido.

Investigar cómo manejar:

```java
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
```

7. Simular error:
    - timestamp con formato inválido
    - Capturar excepción

### 🛠️ Condiciones

- Crear método:
    ```java
    public static ObjectMapper crearMapperConfigurado()
    ```
    Para reutilizar configuración (simulando arquitectura limpia).
- Serializar:
    - Un evento con metadata null (no debe aparecer en JSON).
- Deserializar:
    - Evento con campo extra `"extraField": "valor"`

### 🧠 Aprendes

- Configuración avanzada del ObjectMapper
- Buen diseño reutilizable
- Manejo de propiedades desconocidas
- Listas complejas
- ISO 8601
- Control total del comportamiento de Jackson
- Cómo se configura Jackson en proyectos reales (Spring lo hace automáticamente)
