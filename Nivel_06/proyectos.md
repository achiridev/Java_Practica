# Concurrencia

## 🚀 PROYECTO 1 — Simulador de tareas en un servidor backend

### (Ciclo de vida del hilo + Thread vs Runnable + lambda)

### 📌 Caso real

Estás desarrollando un **servidor backend** que ejecuta tareas en segundo plano (enviar correos, limpiar cache, generar reportes).  
Cada tarea se ejecuta en un hilo independiente.

Tu objetivo es **observar el ciclo de vida real de los hilos** y comparar las formas de crearlos.

### 🧩 Requisitos

- Crear 3 tareas distintas:
  1. Una extendiendo `Thread`
  2. Una implementando `Runnable`
  3. Una usando lambda
- Cada tarea debe imprimir:
  - El nombre del hilo
  - Su estado (`getState()`) antes de iniciar
  - Durante la ejecución
  - Después de terminar
- Simular trabajo con `Thread.sleep(1000)`

### 🛠️ Condiciones

- Usar `Thread.getState()`
- Mostrar los estados: `NEW`, `RUNNABLE`, `TERMINATED`
- Nombrar los hilos con `setName()`
- Ejecutar los 3 hilos desde `main`

### 🧠 Aprendes

- Ciclo de vida real de un hilo
- Diferencias prácticas entre Thread y Runnable
- Forma moderna con lambda
- Cómo los servidores ejecutan tareas paralelas

## 🚀 PROYECTO 2 — Sistema de conteo de visitas (Race Condition)

### (Problema real de concurrencia + synchronized)

### 📌 Caso real

Estás desarrollando un **contador de visitas de una página web** (similar a YouTube o un blog).  
Cada vez que alguien entra, un hilo incrementa el contador global.

Sin sincronización, el número de visitas será incorrecto.

### 🧩 Requisitos

- Crear una clase `ContadorVisitas` con un atributo `visitas`
- Crear 2 o más hilos que incrementen visitas 10000 veces cada uno
- Mostrar el resultado final sin sincronización (debe ser incorrecto)
- Luego corregirlo usando:
  - `synchronized` en método
  - Bloque `synchronized(lock)`

### 🛠️ Condiciones

- Primero implementar sin `synchronized` (ver race condition)
- Luego agregar sincronización y comparar resultados
- Usar `join()` para esperar que los hilos terminen

### 🧠 Aprendes

- Qué es una Race Condition en sistemas reales
- Por qué los contadores globales son peligrosos
- Cómo `synchronized` protege secciones críticas
- Diferencia entre método synchronized y bloque synchronized

## 🚀 PROYECTO 3 — Simulador de transferencia bancaria con Deadlock

### (Deadlock real + synchronized avanzado)

### 📌 Caso real

Estás desarrollando un **sistema bancario** que transfiere dinero entre cuentas.  
Cada cuenta es un recurso compartido con un lock.

Dos transferencias simultáneas pueden causar un **deadlock real**, como en sistemas financieros.

### 🧩 Requisitos

- Crear dos cuentas: `Cuenta A` y `Cuenta B`
- Crear dos hilos:
  - Hilo 1: transfiere de A → B
  - Hilo 2: transfiere de B → A
- Cada transferencia debe usar `synchronized` sobre las cuentas
- Provocar un deadlock bloqueando en orden inverso

### 🛠️ Condiciones

- Usar dos objetos `lockA` y `lockB`
- Hilo 1: sincroniza A → luego B
- Hilo 2: sincroniza B → luego A
- Mostrar mensajes indicando qué lock tiene cada hilo
- Observar que el programa se congela

### 🧠 Aprendes

- Qué es un Deadlock en sistemas reales
- Cómo ocurre en transferencias bancarias, bases de datos, microservicios
- Importancia del orden de adquisición de locks
- Concepto de recursos compartidos críticos

---

# Modern Concurrency

## 🚀 PROYECTO 4 — Procesador de pedidos de una tienda online

👉 Enfocado en: **ExecutorService, Runnable, Callable, FixedThreadPool**

### 📌 Caso real

Una tienda online recibe pedidos y debe procesarlos en paralelo (verificar stock, calcular precio, generar factura).  
Pero el servidor solo puede procesar **máximo 3 pedidos simultáneamente** para no saturar la base de datos.

### 🧩 Requisitos

1. Crear una lista de pedidos (IDs 1 al 10).
2. Cada pedido debe:
    - Simular verificación de stock (sleep 1s)
    - Calcular precio (retornar un número)
3. Usar:
    - `Runnable` para logs
    - `Callable<Double>` para calcular el precio
4. Ejecutar con `ExecutorService` y `FixedThreadPool(3)`.

### 🛠️ Condiciones

- Mostrar qué pedido se está procesando y en qué hilo.
- Obtener el precio usando `Future.get()`.
- Cerrar el executor correctamente con `shutdown()`.

### 🧠 Aprendes

- Qué es un **Thread Pool real**
- Diferencia Runnable vs Callable
- Qué pasa cuando hay más tareas que hilos
- Por qué **no crear hilos manualmente**

## 🚀 PROYECTO 5 — Sistema de validación de usuarios (CompletableFuture pipeline)

👉 Enfocado en: **supplyAsync, thenApply, thenCompose, thenAccept**

### 📌 Caso real

Un sistema de login debe:

1. Buscar usuario en DB
2. Consultar sus permisos en otro servicio
3. Guardar un log cuando termina

Todo debe ser **asíncrono y sin bloquear el hilo principal**.

### 🧩 Requisitos

Implementar funciones simuladas:

```
String obtenerUsuario();
CompletableFuture<List<String>> obtenerPermisosAsync(String user);
void guardarLog(List<String> permisos);
```

Pipeline:

1. `supplyAsync` → obtener usuario
2. `thenCompose` → obtener permisos async
3. `thenApply` → transformar permisos (ej: uppercase)
4. `thenAccept` → guardar log

### 🛠️ Condiciones

- NO usar `.get()`, usar `.join()` al final.
- Mostrar el hilo actual en cada paso.
- Simular delays con `Thread.sleep()`.

## 🚀 PROYECTO 6 — Motor de notificaciones con CompletableFuture + ExecutorService

👉 Enfocado en: **Custom Executor + concurrencia controlada**

### 📌 Caso real

Una app envía notificaciones a usuarios (email, SMS, push).  
El servidor solo permite **2 envíos simultáneos** para no saturar APIs externas.

### 🧩 Requisitos

1. Crear `ExecutorService` con `newFixedThreadPool(2)`.
2. Crear 5 tareas de envío de notificaciones usando `CompletableFuture.supplyAsync(..., executor)`.
3. Cada tarea:
    - Simula enviar notificación (sleep 2s)
    - Retorna "Notificación enviada a Usuario X"
4. Usar:
    - `thenApply` para agregar timestamp
    - `thenAccept` para imprimir en consola

### 🛠️ Condiciones

- Mostrar que solo 2 tareas se ejecutan en paralelo.
- Las otras deben esperar.
- Cerrar executor al final.

### 🧠 Aprendes

- Qué pasa cuando pasas un **executor propio**
- Control real de paralelismo (como en microservicios)
- Diferencia entre ForkJoinPool y tu pool
- Backpressure manual

---

# Fecha y Hora

## 🚀 PROYECTO 7 — Sistema de suscripciones con auditoría y expiración automática

👉 Nivel: **Intermedio → Semi-Senior**

### 📌 Caso real

Una plataforma SaaS (tipo Netflix o GitHub) gestiona suscripciones mensuales y necesita:

- Saber cuándo expira cada suscripción
- Calcular la edad del cliente
- Registrar el momento exacto del pago (timestamp real del servidor)
- Mostrar fechas en formato amigable

### 🧩 Requisitos

1. Crear clase `Suscripcion` con:
    - `String usuario`
    - `LocalDate fechaInicio`
    - `LocalDate fechaVencimiento`
    - `Instant timestampPago`
    - `LocalDate fechaNacimientoCliente`
2. Calcular:
    - Fecha de vencimiento = fechaInicio + 30 días
    - Edad del cliente usando `Period`
    - Si la suscripción está vencida
    - Días restantes hasta vencimiento
3. Formatear fechas en:
    - `"dd/MM/yyyy"` para mostrar al usuario.

### 🛠️ Condiciones

- Usar:
  - `Period.between()` para edad
  - `LocalDate.plusDays()`
  - `Instant.now()` para registrar pago
  - `DateTimeFormatter` para mostrar fechas
- Convertir `Instant → LocalDateTime`
- Mostrar reporte:

```
Usuario: Juan
Edad: 25 años
Inicio: 10/01/2026
Vence: 09/02/2026
Pago registrado en: 2026-01-29T15:22:10Z
Estado: ACTIVA
Días restantes: 12
```

### 🧠 Aprendes

- Timestamp real de servidor (logs, pagos, auditoría)
- Cálculo de edad (uso real de Period)
- Formateo profesional de fechas
- Conversión entre Instant y LocalDateTime

## 🚀 PROYECTO 8 — Sistema avanzado de control de asistencia con métricas

👉 Nivel: **Intermedio Alto**

### 📌 Caso real

Una empresa registra entrada/salida de empleados y necesita:

- Horas trabajadas
- Horas extra
- Tiempo total en minutos
- Timestamp exacto del registro (para auditoría)
- Reporte formateado

### 🧩 Requisitos

1. Crear clase `RegistroAsistencia` con:
    - `String empleado`
    - `LocalDate fecha`
    - `LocalTime horaEntrada`
    - `LocalTime horaSalida`
    - `Instant timestampRegistro`
2. Calcular:
    - Horas trabajadas con `Duration`
    - Horas extra (> 8h)
    - Si llegó tarde (después de 09:00)
    - Tiempo total en minutos
3. Mostrar reporte formateado con `DateTimeFormatter`.

### 🛠️ Condiciones

- Convertir `LocalDate + LocalTime → LocalDateTime`
- Usar:
  - `Duration.between()`
  - `LocalTime.isAfter()`
  - `DateTimeFormatter "dd-MM-yyyy HH:mm"`
- Mostrar:

```
Empleado: Maria
Fecha: 29-01-2026
Entrada: 09:15
Salida: 18:45
Horas trabajadas: 9h 30m
Horas extra: 1h 30m
Registro timestamp: 2026-01-29T14:22:10Z
```

### 🧠 Aprendes

- Duration real (muy usado en logs, métricas)
- Cálculo de tiempo laboral
- Auditoría con Instant
- Formatos profesionales de fecha/hora

## 🚀 PROYECTO 9 — Motor global de reservas con UTC y conversión de zonas

👉 Nivel: **Avanzado (muy usado en empresas reales)**

### 📌 Caso real

Un sistema global de reservas (tipo Booking o Google Calendar) guarda eventos en UTC pero muestra la hora local al usuario.

### 🧩 Requisitos

1. El usuario ingresa una fecha como texto:
    - `"29/01/2026 10:00"`
2. Parsear a `LocalDateTime`.
3. Convertir a `ZonedDateTime` en zona del usuario (`America/Lima`).
4. Convertir a `Instant` para guardar en base de datos.
5. Mostrar el evento en:
    - Perú
    - New York
    - Tokio

### 🛠️ Condiciones

- Usar:
  - `DateTimeFormatter.parse()`
  - `ZonedDateTime.of()`
  - `Instant`
  - `withZoneSameInstant()`
- Mostrar:

```
Evento original (Perú): 29/01/2026 10:00
Evento UTC timestamp: 2026-01-29T15:00:00Z
New York: 29/01/2026 10:00
Tokio: 30/01/2026 00:00
```

### 🧠 Aprendes

- Conversión real de zonas horarias
- Cómo funcionan Google Calendar, Zoom, Booking
- Guardar en UTC (estándar mundial)
- Parseo de input de usuario (muy backend)
