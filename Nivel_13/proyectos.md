# Spring Web

## 🚀 PROYECTO 1 — API de Gestión de Pedidos (E-commerce)

### 📌 Caso real

Una tienda online necesita un backend para gestionar pedidos.

Los clientes pueden:

- Crear pedidos
- Consultar sus pedidos
- Filtrar pedidos por estado (pendiente, enviado, entregado)
- Actualizar el estado de un pedido
- Eliminar pedidos cancelados

👉 Este tipo de API es básico en cualquier sistema tipo **Amazon, Mercado Libre, etc.**

### 🧩 Requisitos

#### 🔹 Endpoints principales

#### 1. Crear pedido

```http
POST /api/pedidos
```

Body:

```json
{  
  "cliente": "Daniel",  
  "total": 150.5,  
  "estado": "PENDIENTE"  
}
```

#### 2. Obtener todos los pedidos (con filtros)

```http
GET /api/pedidos?estado=PENDIENTE&page=0&size=5
```

Usar:

- `@RequestParam` (filtros y paginación)
- parámetros opcionales

#### 3. Obtener pedido por ID

```http
GET /api/pedidos/{id}
```

Usar:

- `@PathVariable`

#### 4. Actualizar pedido completo

```http
PUT /api/pedidos/{id}
```

#### 5. Actualizar estado (parcial)

```http
PATCH /api/pedidos/{id}?estado=ENVIADO
```

👉 Aquí mezclas:

- `@PathVariable`
- `@RequestParam`

#### 6. Eliminar pedido

```http
DELETE /api/pedidos/{id}
```

### 🛠️ Condiciones

- Usar:
  - `@RestController`
  - `@RequestMapping("/api/pedidos")`
- No usar base de datos (usa lista en memoria)
- Validar:
  - que el pedido exista antes de actualizar/eliminar
- Separar en capas:
  - Controller
  - Service

### 📤 Posibles salidas

#### ✔️ Crear pedido

```json
{  
  "id": 1,  
  "cliente": "Daniel",  
  "total": 150.5,  
  "estado": "PENDIENTE"  
}
```

#### ✔️ Filtro

```json
[  
  {  
    "id": 2,  
    "cliente": "Ana",  
    "estado": "PENDIENTE"  
  }  
]
```

#### ❌ Error

```json
{  
  "error": "Pedido no encontrado"  
}
```

### 🧠 Aprendes

- Diseño REST real
- Uso combinado de:
  - `@PathVariable`
  - `@RequestParam`
  - `@RequestBody`
- Diferencia entre:
  - `PUT` vs `PATCH`
- Buenas prácticas de rutas (`/api/pedidos`)
- Manejo de estados en sistemas reales

---

## 🚀 PROYECTO 2 — API de Registro y Autenticación de Usuarios

### 📌 Caso real

Una aplicación (tipo red social o e-commerce) necesita un sistema de registro de usuarios.

Problemas reales que debes resolver:

- Usuarios envían datos inválidos ❌
- Emails duplicados ❌
- Errores inconsistentes ❌

👉 Necesitas respuestas HTTP correctas + validaciones + errores estructurados.

### 🧩 Requisitos

#### 🔹 1. Registro de usuario

```http
POST /api/auth/register
```

Body:

```JSON
{  
  "nombre": "Daniel",  
  "email": "daniel@gmail.com",  
  "password": "12345678",  
  "edad": 25  
}
```

✔ Usar:

- DTO con:
  - `@NotBlank`
  - `@Email`
  - `@Size`
  - `@Min`

✔ Usar:

- `@Valid`
- `ResponseEntity`

#### 🔹 2. Obtener usuario por ID

```http
GET /api/auth/{id}
```

✔ Si no existe:

- lanzar excepción personalizada → manejar con `@RestControllerAdvice`

#### 🔹 3. Eliminar usuario

```http
DELETE /api/auth/{id}
```

✔ Respuesta:

- `204 NO CONTENT`

#### 🔹 4. Login (simulado)

```http
POST /api/auth/login
```

Body:

```JSON
{  
  "email": "daniel@gmail.com",  
  "password": "12345678"  
}
```

✔ Si credenciales incorrectas:

- `400 BAD REQUEST`

### 🛠️ Condiciones

- ❌ NO devolver objetos directamente → usar `ResponseEntity`
- ✔ Usar:
  - `ResponseEntity<Objeto>`
  - `ResponseEntity<String>`
  - `ResponseEntity<Void>`
- ✔ Implementar:
  - DTOs
  - Validaciones con `@Valid`
  - `@RestControllerAdvice`
- ✔ Crear:
  - `ErrorResponse` (JSON estructurado)
- ✔ Manejar excepciones:
  - Usuario no encontrado → `404`
  - Email duplicado → `400`
  - Error general → `500`
- ✔ Usar headers:

Location: `/api/auth/{id}`

### 📤 Posibles salidas

#### ✔️ Registro exitoso (201)

```JSON
{  
  "id": 1,  
  "nombre": "Daniel",  
  "email": "daniel@gmail.com"  
}
```

Headers:

```http
Location: /api/auth/1
```

#### ❌ Error validación (400)

```JSON
{  
  "email": "El email no es válido",  
  "password": "Debe tener al menos 8 caracteres"  
}
```

#### ❌ Usuario no encontrado (404)

```JSON
{  
  "status": 404,  
  "mensaje": "Usuario no encontrado con id: 10",  
  "timestamp": "2026-03-25T12:00:00"  
}
```

#### ❌ Login incorrecto (400)

```JSON
{  
  "status": 400,  
  "mensaje": "Credenciales inválidas"  
}
```

### 🧠 Aprendes

- Por qué **NO devolver objetos directamente**
- Uso real de:
  - `ResponseEntity`
  - `HttpStatus`
- Validación profesional con DTOs
- Manejo global de errores (`@RestControllerAdvice`)
- Uso de headers (`Location`)
- Diferencia entre:
  - error de negocio (400)
  - recurso inexistente (404)
  - error interno (500)

---
