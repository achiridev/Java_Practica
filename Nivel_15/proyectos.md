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
