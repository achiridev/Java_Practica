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
