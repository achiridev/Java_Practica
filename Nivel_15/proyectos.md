# Spring Security

## 🚀 PROYECTO 1 — API de Autenticación con JWT (Sistema Base Profesional)

### 📌 Caso real

Una aplicación (tipo app móvil o SPA) necesita autenticación **sin sesiones**.

Problema real:

- No se pueden usar cookies tradicionales
- Se necesita seguridad stateless
- Cada request debe validarse con token

👉 Esto es exactamente cómo funcionan:

- apps móviles
- APIs modernas
- microservicios

### 🧩 Requisitos

#### 🔹 1. Registro

```http
POST /api/auth/register
```

✔ Guardar usuario en BD  
✔ Encriptar contraseña con `BCryptPasswordEncoder`

#### 🔹 2. Login

```http
POST /api/auth/login
```

✔ Validar credenciales  
✔ Generar JWT con:

- id
- email
- rol
- fecha expiración

#### 🔹 3. Endpoint protegido

```http
GET /api/user/perfil
```

✔ Solo accesible con token válido

#### 🔹 4. Endpoint público

```http
GET /api/public/info
```

✔ Sin autenticación

### 🛠️ Condiciones

- ✔ Configurar:

```java
SecurityFilterChain
```

- ✔ Desactivar:

```java
csrf.disable()
```

- ✔ Stateless:

```java
sessionCreationPolicy(STATELESS)
```

- ✔ Crear:
    - `JwtUtil` (generar/validar token)
    - `JwtFilter` (`OncePerRequestFilter`)
    - `UserDetailsService`
- ✔ Leer token desde:

```http
Authorization: Bearer <token>
```

### 📤 Posibles salidas

#### ✔️ Login exitoso

```json
{  
  "token": "eyJhbGciOiJIUzI1NiIs..."  
}
```

#### ❌ Token inválido

```json
{  
  "mensaje": "Token inválido o expirado"  
}
```

#### ✔️ Acceso autorizado

```json
{  
  "email": "daniel@gmail.com",  
  "rol": "USER"  
}
```

### 🧠 Aprendes

- Arquitectura moderna con `SecurityFilterChain`
- Flujo completo JWT
- Filtros personalizados (`OncePerRequestFilter`)
- Seguridad stateless real
- Cómo funciona la autenticación en APIs modernas

---

## 🚀 PROYECTO 2 — Sistema de Roles y Permisos (Nivel PRO 🔥)

### 📌 Caso real

Sistema empresarial donde no todos los usuarios tienen los mismos permisos.

Ejemplo real:

- Admin → puede eliminar usuarios
- Moderador → puede editar contenido
- Usuario → solo puede ver

👉 Esto es lo que usan:

- sistemas bancarios
- paneles administrativos
- ERPs

### 🧩 Requisitos

#### 🔹 Roles

- ROLE_USER
- ROLE_MODERATOR
- ROLE_ADMIN

#### 🔹 Permisos (más fino 🔥)

- READ_PRODUCT
- CREATE_PRODUCT
- DELETE_PRODUCT
- UPDATE_PRODUCT

#### 🔹 Endpoints

#### Público

```http
GET /api/public/productos
```

#### Usuario

```http
GET /api/user/compras
```

#### Moderador

```http
PUT /api/mod/productos/{id}
```

#### Admin

```http
DELETE /api/admin/productos/{id}
```

### 🛠️ Condiciones

- ✔ Usar:

```java
requestMatchers("/api/admin/**").hasRole("ADMIN")  
requestMatchers("/api/mod/**").hasAnyRole("MODERATOR", "ADMIN")
```

#### 🔥 NIVEL AVANZADO (IMPORTANTE)

👉 No solo roles → también permisos

✔ Implementar:

- entidad `Role`
- entidad `Permission`
- relación:

```java
@ManyToMany
```

✔ Usar:

```java
@PreAuthorize("hasAuthority('DELETE_PRODUCT')")
```

✔ JWT debe contener:

- roles
- permisos

✔ Crear:

- `CustomUserDetails`
- `UserDetailsService`

### 📤 Posibles salidas

#### ❌ Acceso denegado

```json
{  
  "mensaje": "Acceso denegado"  
}
```

#### ✔️ Admin elimina producto

```java
{  
  "mensaje": "Producto eliminado"  
}
```

### ❌ Usuario intenta eliminar

```java
{  
  "mensaje": "No tienes permisos"  
}
```

### 🧠 Aprendes

- Diferencia REAL:
  - roles vs permisos
- Seguridad a nivel empresa
- `@PreAuthorize` (muy usado en trabajo)
- Modelado complejo de seguridad
- JWT con información avanzada

👉 Este proyecto solo ya te sube mucho de nivel

---
