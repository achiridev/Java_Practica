# Spring Data JPA

## 🚀 PROYECTO 1 — Sistema de Blog (Usuarios, Posts y Comentarios)

### 📌 Caso real

Quieres construir el backend de un blog tipo:

- Medium
- Dev.to
- LinkedIn posts

Problema real:

- Un usuario puede tener muchos posts
- Un post puede tener muchos comentarios
- Necesitas evitar problemas de rendimiento (N+1)

### 🧩 Requisitos

#### 🔹 Entidades

- Usuario
- Post
- Comentario

Relaciones:

- Usuario → Posts → `@OneToMany`
- Post → Comentarios → `@OneToMany`
- Comentario → Usuario → `@ManyToOne`

### 🔹 Funcionalidades

#### 1. Crear usuario

```http
POST /api/usuarios
```

#### 2. Crear post

```http
POST /api/posts
```

✔ Relacionado a un usuario

#### 3. Crear comentario

```http
POST /api/comentarios
```

✔ Relacionado a:

- usuario
- post

#### 4. Obtener posts con paginación

```http
GET /api/posts?page=0&size=5
```

✔ usar `Pageable`

#### 5. Buscar posts por título

```java
findByTituloContaining(String titulo)
```

#### 6. Top 5 posts más recientes

```java
findTop5ByOrderByFechaDesc()
```

#### 7. Obtener post con comentarios (problema N+1 🔥)

👉 Resolver usando:

- `@Query` con `JOIN FETCH`

### 🛠️ Condiciones

- ✔ Usar:
  - `@Entity`
  - `@Table`
  - `@Id`
- ✔ Relaciones:
  - `FetchType.LAZY` (obligatorio)
- ✔ Crear:
  - `JpaRepository`
- ✔ Implementar:
  - consultas derivadas
  - `@Query` con JPQL

💡 Bonus:

- evitar N+1 con:

```java
@Query("SELECT p FROM Post p JOIN FETCH p.comentarios WHERE p.id = :id")
```

### 📤 Posibles salidas

#### ✔️ Post con comentarios

```JSON
{  
  "id": 1,  
  "titulo": "Spring Boot",  
  "comentarios": [  
    { "texto": "Buen post" }  
  ]  
}
```

#### ✔️ Paginación

```JSON
{  
  "content": [...],  
  "totalPages": 5,  
  "totalElements": 25  
}
```

### 🧠 Aprendes

- Relaciones reales en BD
- `LAZY vs EAGER` (clave en entrevistas)
- Problema N+1 y solución
- `Pageable` en producción
- JPQL con `JOIN FETCH`

---

## 🚀 PROYECTO 2 — Sistema de Ventas (Pedidos, Clientes y Productos)

### 📌 Caso real

Sistema tipo:

- tienda online
- ERP básico
- sistema de facturación

Problema real:

- Un cliente hace pedidos
- Un pedido tiene muchos productos
- Todo debe ser transaccional

### 🧩 Requisitos

#### 🔹 Entidades

- Cliente
- Pedido
- Producto

Relaciones:

- Cliente → Pedidos → `@OneToMany`
- Pedido → Productos → `@ManyToMany`

#### 🔹 Funcionalidades

##### 1. Crear cliente

##### 2. Crear producto

##### 3. Crear pedido (🔥 IMPORTANTE)

```http
POST /api/pedidos
```

✔ Incluye:

- cliente
- lista de productos

✔ calcular total automáticamente

##### 4. Obtener pedidos con paginación

```http
GET /api/pedidos?page=0&size=10
```

##### 5. Buscar pedidos por cliente

```java
findByClienteId(Long id)
```

##### 6. Top pedidos por monto

```java
findTop5ByOrderByTotalDesc()
```

### 🛠️ Condiciones

- ✔ Usar `@Transactional` en creación de pedido

👉 Caso real:

- si falla guardar productos → rollback completo
- ✔ Usar:
  - `JpaRepository`
  - relaciones correctas
- ✔ Evitar:
  - cargar todo con `EAGER`

💡 Bonus:

- usar `@Query` para reportes:

```java
@Query("SELECT p FROM Pedido p WHERE p.total > :monto")
```

### 📤 Posibles salidas

#### ✔️ Pedido creado

```JSON
{  
  "id": 1,  
  "cliente": "Daniel",  
  "total": 500  
}
```

#### ❌ Error (rollback)

```JSON
{  
  "mensaje": "Error al crear pedido"  
}
```

### 🧠 Aprendes

- Transacciones reales (`@Transactional`)
- Relaciones complejas (`ManyToMany`)
- Lógica de negocio (cálculo de totales)
- Queries derivadas avanzadas
- Backend tipo empresa

---
