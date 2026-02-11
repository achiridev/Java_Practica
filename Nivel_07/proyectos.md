# JDBC Puro

## 🚀 PROYECTO 1 — Sistema de login seguro + demostración de SQL Injection

👉 Nivel: **Intermedio (fundamentos sólidos)**

### 📌 Caso real

Una aplicación tiene un sistema de login.  
Primero se implementó de forma insegura y sufrió un ataque de **SQL Injection**.  
Ahora debes **demostrar el problema** y luego **corregirlo correctamente**.

### 🧩 Requisitos

1. Tener una tabla `usuarios`:
`id INT username VARCHAR password VARCHAR email VARCHAR`
2. Implementar **dos métodos**:
    - `loginInseguro(String user, String pass)` usando `Statement`
    - `loginSeguro(String user, String pass)` usando `PreparedStatement`
3. El login debe:
    - Buscar usuario por `username` y `password`
    - Retornar un objeto `Usuario` si existe
    - Retornar `null` si no existe

### 🛠️ Condiciones

- En el método inseguro:
  - Construir el SQL concatenando strings.
  - Probar con una entrada maliciosa (`' OR '1'='1`).
- En el método seguro:
  - Usar `?` y `setString()`.
  - Ejecutar con `executeQuery()`.
- Mapear el `ResultSet → Usuario`.
- Usar `if (rs.next())` (SELECT de una sola fila).

### 🧠 Aprendes

- **Por qué Statement es peligroso**
- SQL Injection real (no teórico)
- Uso correcto de `PreparedStatement`
- SELECT de una sola fila
- Mapeo manual seguro

## 🚀 PROYECTO 2 — CRUD de productos con métricas de filas afectadas

👉 Nivel: **Intermedio–Alto**

### 📌 Caso real

Una tienda online administra productos.  
El sistema debe permitir **crear, listar, actualizar y eliminar** productos desde base de datos.

### 🧩 Requisitos

1. Tabla `productos`:
  `id INT nombre VARCHAR precio DECIMAL stock INT fecha_creacion TIMESTAMP`
2. Implementar métodos:
    - `insertarProducto(Producto p)`
    - `actualizarPrecio(int id, double nuevoPrecio)`
    - `eliminarProducto(int id)`
    - `listarProductos()`
3. Crear clase `Producto` con todos los campos.

### 🛠️ Condiciones

- Usar **solo PreparedStatement**.
- Usar:
  - `executeUpdate()` para INSERT / UPDATE / DELETE
  - `executeQuery()` para SELECT
- Mostrar:
  - Número de filas afectadas en cada operación.
- En `listarProductos()`:
  - Usar `while (rs.next())`
  - Mapear manualmente a `List<Producto>` 
- Usar:
  - `setString`, `setDouble`, `setInt`, `setTimestamp`

### 🧠 Aprendes

- CRUD real con JDBC
- Diferencia clara entre `executeQuery` y `executeUpdate`
- Interpretar filas afectadas
- Mapeo de múltiples filas
- JDBC como en proyectos legacy reales

## 🚀 PROYECTO 3 — Sistema de órdenes con búsqueda dinámica y mapeo complejo

👉 Nivel: **Avanzado (nivel backend junior fuerte)**

### 📌 Caso real

Un sistema de órdenes necesita buscar pedidos con filtros dinámicos:

- Por usuario
- Por rango de fechas
- Por monto mínimo

Los filtros pueden venir o no venir desde el frontend.

### 🧩 Requisitos

1. Tabla `ordenes`:

```
id INT
usuario VARCHAR
total DECIMAL
fecha TIMESTAMP
estado VARCHAR
```

2. Crear clase `Orden`.
3. Implementar método:

```
List<Orden> buscarOrdenes(
    String usuario,
    LocalDateTime desde,
    LocalDateTime hasta,
    Double montoMinimo
)
```

### 🛠️ Condiciones

- Construir el SQL base:
`SELECT id, usuario, total, fecha, estado FROM ordenes WHERE 1=1`
- Agregar condiciones **solo si el parámetro no es null**.
- Usar `PreparedStatement` con índices dinámicos.
- Convertir `LocalDateTime → Timestamp`.
- Usar `executeQuery()`.
- Mapear cada fila del `ResultSet` a `Orden`.

### 🧠 Aprendes

- SQL dinámico seguro
- Manejo de parámetros opcionales
- Uso avanzado de PreparedStatement
- Conversión Java Time → SQL
- Mapeo complejo de ResultSet
- Base para repositorios tipo DAO / Repository
