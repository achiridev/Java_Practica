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

# Transacciones

## 🚀 PROYECTO 4 — Transferencia bancaria con garantía ACID

👉 Nivel: **Intermedio–Avanzado**

### 📌 Caso real

Un sistema bancario permite transferir dinero entre cuentas.  
La operación debe cumplir ACID:

- No puede perder dinero.
- No puede duplicar dinero.
- Si algo falla, TODO debe revertirse.

### 🧩 Requisitos

Tabla `cuentas`:

```
id INT PRIMARY KEY
titular VARCHAR(100)
saldo DECIMAL(10,2)
```

Implementar método:

`void transferir(int cuentaOrigenId, int cuentaDestinoId, double monto)`

La transferencia debe:

1. Verificar que la cuenta origen tenga saldo suficiente.
2. Restar saldo a cuenta origen.
3. Sumar saldo a cuenta destino.
4. Confirmar cambios.

### 🛠️ Condiciones

- Desactivar auto-commit:

`connection.setAutoCommit(false);`

- Usar:
  - `SELECT ... FOR UPDATE` (si tu BD lo soporta)
  - `executeQuery()` para leer saldo
  - `executeUpdate()` para actualizar
- Si ocurre cualquier error:

`connection.rollback();`
- Si todo sale bien:

`connection.commit();`

- Manejar correctamente:
  - SQLException
  - Saldo insuficiente

### 🧠 Aprendes

- Atomicidad real
- Por qué no se debe usar auto-commit en operaciones críticas
- Cómo evitar inconsistencias
- Fundamentos de sistemas bancarios

## 🚀 PROYECTO 5 — Creación de pedido con múltiples inserts dependientes

👉 Nivel: **Avanzado**

### 📌 Caso real

Un e-commerce crea un pedido que incluye:

- Insertar orden en tabla `ordenes`
- Insertar múltiples registros en `orden_items`
- Actualizar stock en `productos`

Si falla cualquier paso, TODO debe revertirse.

### 🧩 Requisitos

Tablas:

```
ordenes (id, usuario, total, fecha)
orden_items (id, orden_id, producto_id, cantidad, precio)
productos (id, nombre, stock, precio)
```

Implementar método:

`void crearOrden(String usuario, List<ItemDTO> items)`

Debe:

1. Insertar la orden.
2. Obtener ID generado.
3. Insertar cada item.
4. Descontar stock.
5. Confirmar transacción.

### 🛠️ Condiciones

- Usar:

`connection.setAutoCommit(false);`

- Usar:
  - `PreparedStatement.RETURN_GENERATED_KEYS`
- Si un producto no tiene stock suficiente:
  - Lanzar excepción
  - Ejecutar rollback
- Si todo es correcto:
  - Ejecutar commit

### 🧠 Aprendes

- Transacciones con múltiples tablas
- Integridad referencial
- Uso de claves generadas
- Cómo funcionan los sistemas de compra reales

## 🚀 PROYECTO 6 — Sistema de reserva con control de concurrencia

👉 Nivel: **Muy Avanzado (Junior fuerte / Semi Senior)**

### 📌 Caso real

Un sistema de reservas de hotel permite reservar habitaciones.

Problema:  
Dos usuarios pueden intentar reservar la misma habitación al mismo tiempo.

Debes evitar:

- Doble reserva
- Inconsistencias
- Condiciones de carrera

### 🧩 Requisitos

Tabla:

```
habitaciones (id, numero, disponible BOOLEAN)
reservas (id, habitacion_id, usuario, fecha)
```

Implementar método:

`void reservarHabitacion(int habitacionId, String usuario)`

Debe:

1. Verificar si la habitación está disponible.
2. Marcarla como no disponible.
3. Insertar reserva.
4. Confirmar.

### 🛠️ Condiciones

- Desactivar auto-commit.
- Usar:

`SELECT ... FOR UPDATE`

para bloquear la fila.

- Simular concurrencia (opcional pero recomendado).
- Si la habitación ya está reservada:
  - Lanzar excepción
  - Hacer rollback
- Confirmar solo si todo es correcto.

### 🧠 Aprendes

- Control de concurrencia real
- Bloqueo de filas
- Problemas tipo race condition
- Aislamiento de transacciones
- Base para sistemas tipo Booking

---
