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

# 🚀 PROYECTO 2 — API de Usuarios con Búsqueda Avanzada

## 📌 Caso real

Una empresa necesita un sistema para gestionar usuarios con búsqueda avanzada.

Se debe poder:

- Crear usuarios
- Buscar por múltiples filtros
- Activar/desactivar usuarios
- Actualizar datos parcialmente

👉 Esto es exactamente lo que hacen sistemas de:

- CRM
- sistemas administrativos
- dashboards internos

---

## 🧩 Requisitos

### 🔹 Modelo Usuario

{  
  "id": 1,  
  "nombre": "Daniel",  
  "edad": 25,  
  "activo": true  
}

---

### 1. Crear usuario

POST /api/usuarios

---

### 2. Buscar usuarios (endpoint clave 🔥)

GET /api/usuarios?nombre=daniel&edad=25&activo=true

Usar:

- `@RequestParam(required = false)`
- múltiples filtros dinámicos

💡 Debe funcionar aunque envíen solo 1 parámetro

---

### 3. Obtener usuario por ID

GET /api/usuarios/{id}

---

### 4. Actualizar completamente

PUT /api/usuarios/{id}

---

### 5. Actualización parcial (clave 🔥)

PATCH /api/usuarios/{id}

Body:

{  
  "activo": false  
}

👉 Aquí usas `@RequestBody` parcialmente

---

### 6. Activar/desactivar usuario (endpoint específico)

PATCH /api/usuarios/{id}/estado?activo=false

---

### 7. Eliminar usuario

DELETE /api/usuarios/{id}

---

## 🛠️ Condiciones

- Usar:
    - `@RestController`
    - `@RequestMapping("/api/usuarios")`
- Manejar:
    - parámetros opcionales (`required = false`)
    - valores por defecto
- Validar:
    - edad no negativa
- No usar base de datos (lista en memoria)

💡 Bonus PRO:

- implementar paginación:

GET /api/usuarios?page=0&size=10

---

## 📤 Posibles salidas

### ✔️ Búsqueda flexible

[  
  {  
    "id": 1,  
    "nombre": "Daniel",  
    "activo": true  
  }  
]

---

### ✔️ Actualización parcial

{  
  "id": 1,  
  "nombre": "Daniel",  
  "edad": 25,  
  "activo": false  
}

---

### ❌ Error validación

{  
  "error": "Edad inválida"  
}

---

## 🧠 Aprendes

- Búsquedas reales tipo backend profesional
- Uso avanzado de:
    - `@RequestParam` opcionales
- Diferencia entre:
    - filtros vs rutas
- `PATCH` real (muy importante en APIs modernas)
- Diseño limpio de endpoints REST
