# Spring Boot Fundamentals

## 🚀 PROYECTO 1 — API de Configuración de Aplicación

### 📌 Caso real

Una empresa quiere una API que exponga información de su sistema dependiendo del entorno:

- En **DEV** muestra información detallada
- En **PROD** muestra información limitada

👉 Esto se usa mucho para:

- health endpoints
- info endpoints
- debugging

### 🧩 Requisitos

1. Crear un endpoint:

```http
GET /info
```

2. Debe devolver:

```JSON
{  
  "nombre": "...",  
  "version": "...",  
  "entorno": "...",  
  "debug": true/false  
}
```

3. Configuración en `application.yml`:

```YAML
mi:  
  app:  
    nombre: "Sistema de Ventas"  
    version: "1.0"
```

4. Configuración por perfiles:

#### `application-dev.yml`

```YAML
mi:  
  app:  
    entorno: "DEV"  
    debug: true
```

#### `application-prod.yml`

```YAML
mi:  
  app:  
    entorno: "PROD"  
    debug: false
```

5. Usar:

👉 `@ConfigurationProperties` para mapear TODO

### 🛠️ Condiciones

- ❌ No usar `@Value` para todo
- ✅ Usar una clase tipo:

```java
@ConfigurationProperties(prefix = "mi.app")
```

- ✔ Usar inyección por constructor
- ✔ Separar en:
  - controller
  - service
  - config

### 🧠 Aprendes

- Configuración centralizada
- Uso real de perfiles
- Buen diseño de config en backend
- Diferencia real entre entornos

## 🚀 PROYECTO 2 — Cliente de API Externa configurable

### 📌 Caso real

Tu backend consume una API externa (ej: pagos, clima, etc.).

👉 Pero:

- En DEV usas API fake
- En PROD usas API real

### 🧩 Requisitos

1. Endpoint:

`GET /clima`

2. Configuración:

#### `application.yml`

```YAML
api:  
  clima:  
    timeout: 3000
```

#### `application-dev.yml`

```YAML
api:  
  clima:  
    url: https://api.fake.com
```

#### `application-prod.yml`

```YAML
api:  
  clima:  
    url: https://api.real.com
```

3. Leer configuración:

👉 Usa `@ConfigurationProperties`

4. Simular llamada API:

```java
public String obtenerClima() {  
    return "Llamando a: " + url;  
}
```

5. Además:

👉 Usa `@Value` SOLO para una propiedad simple:

```YAML
app:  
  nombre: "Cliente Clima"
```

### 🛠️ Condiciones

- ✔ Separar lógica en `Service`
- ✔ Configuración en clase dedicada
- ✔ No hardcodear URLs
- ✔ Usar profiles correctamente

### 🧠 Aprendes

- Configuración externa real
- Integración con APIs
- Cuándo usar `@Value` vs `@ConfigurationProperties`
- Simulación de microservicios

## 🚀 PROYECTO 3 — Sistema de Logs y Features por entorno

### 📌 Caso real

En una empresa:

- En DEV quieres logs detallados
- En PROD quieres logs mínimos
- Algunas funcionalidades solo existen en DEV

### 🧩 Requisitos

1. Configuración:

#### `application.yml`

```YML
feature:  
  experimental: false
```

#### `application-dev.yml`

```YML
logging:  
  level:  
    root: DEBUG  
  
feature:  
  experimental: true
```

#### `application-prod.yml`

```YML
logging:  
  level:  
    root: WARN  
  
feature:  
  experimental: false
```

2. Crear endpoint:

```http
GET /feature
```

3. Lógica:

- Si `experimental = true` → devolver:

`"Feature experimental activa"`

- Si no:

`"Feature desactivada"`

4. BONUS (IMPORTANTE):

👉 Crear dos servicios:

```java
@Profile("dev")  
public class FeatureDevService
```

```java
@Profile("prod")  
public class FeatureProdService
```

### 🛠️ Condiciones

- ✔ Usar `@Profile`
- ✔ Usar `@Value` para feature simple
- ✔ Logs configurados por YAML
- ✔ No usar `if` innecesarios → usa perfiles

### 🧠 Aprendes

- Uso real de `@Profile`
- Configuración de logs
- Feature flags
- Diferenciación de comportamiento por entorno
