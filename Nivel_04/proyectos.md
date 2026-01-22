# Lambdas

## 🚀 PROYECTO 1 — Calculadora flexible (reglas de negocio variables)

### 📌 Caso real

En un sistema financiero, las operaciones matemáticas cambian según el contexto (suma normal, suma con impuesto, resta con comisión, etc.).
No quieres modificar el código base cada vez que cambia la regla.

### 🧩 Requisitos

- Crear una interfaz funcional Operacion con un método:
  - int operar(int a, int b)
- Crear una clase Calculadora
- La calculadora debe recibir una Operacion y ejecutar el cálculo

### 🛠️ Condiciones

- Implementar al menos 3 lambdas distintas:
  - Suma normal
  - Resta
  - Suma con lógica adicional (bloque {} y return)
- Usar lambda de una línea y lambda con bloque
- No crear clases anónimas

### 🧠 Aprendes

- Qué es una interfaz funcional y por qué representa una función
- Uso real de lambdas para reglas cambiantes
- Diferencia entre lambda simple y lambda con bloque
- Por qué @FunctionalInterface protege tu diseño

## 🚀 PROYECTO 2 — Sistema de notificaciones (comportamiento variable)

### 📌 Caso real

Un backend envía notificaciones por distintos medios:

- Consola
- Logs
- Mensajes personalizados  
  El **qué hacer con el mensaje cambia**, pero el flujo es el mismo.

### 🧩 Requisitos

- Crear una **interfaz funcional** `Notificacion`
  - Método: `void enviar(String mensaje)`
- Crear una clase `ServicioNotificacion`
- El servicio debe ejecutar la notificación recibida

### 🛠️ Condiciones

- Implementar notificaciones usando **lambdas**
- Usar:
  - Lambda sin paréntesis (un parámetro)
  - Lambda con paréntesis
- Al menos una lambda debe **imprimir**
- Otra debe **formatear el mensaje**

### 🧠 Aprendes

- Lambdas con métodos `void`
- Sintaxis compacta vs explícita
- Cómo una interfaz funcional define el “qué” y la lambda el “cómo”
- Analogía función → implementación concreta

## 🚀 PROYECTO 3 — Validador de reglas (lógica enchufable)

### 📌 Caso real

Un sistema necesita validar datos de distintas formas:

- Edad válida
- Contraseña fuerte
- Número positivo  
    Las reglas **no deben estar fijas** en el código.

### 🧩 Requisitos

- Crear una **interfaz funcional** `Validador<T>`
  - Método: `boolean validar(T valor)`
- Crear una clase `MotorValidacion`
- El motor debe ejecutar la validación recibida

### 🛠️ Condiciones

- Usar **genéricos + lambdas**
- Crear validadores con:
  - Lambda de una línea
  - Lambda con bloque
- Probar con distintos tipos (`Integer`, `String`)
- Usar `@FunctionalInterface`

### 🧠 Aprendes

- Interfaz funcional genérica
- Lambdas como reglas reutilizables
- Diferencia entre **definir el contrato** y **definir la lógica**
- Diseño flexible sin if/else gigantes

---
# Paquete java.util.function

## 🚀 PROYECTO 4 — Registro y validación de usuarios

### 📌 Caso real

Un sistema debe **registrar usuarios**, pero antes necesita:

- Validar datos
- Transformarlos
- Mostrar información
- Generar valores por defecto

### 🧩 Requisitos

- Clase `Usuario`:
  - `nombre`
  - `edad`
- Usar:
  - `Predicate<Usuario>` → validar edad
  - `Function<Usuario, String>` → transformar a texto
  - `Consumer<String>` → mostrar o registrar en log
  - `Supplier<Usuario>` → generar un usuario por defecto

### 🛠️ Condiciones

- El `Predicate` debe validar si el usuario es mayor de edad
- El `Function` debe convertir el usuario a un mensaje legible
- El `Consumer` debe imprimir el mensaje
- El `Supplier` debe crear un usuario genérico si no se recibe uno
- Usar `test()`, `apply()`, `accept()`, `get()`

### 🧠 Aprendes

- Flujo completo usando interfaces funcionales del JDK
- Cuándo validar, transformar, consumir o generar
- Diferencia clara entre cada interfaz
- Uso real de `java.util.function`

## 🚀 PROYECTO 5 — Procesamiento de productos (pipeline simple)

### 📌 Caso real

Un sistema de inventario procesa productos en varios pasos:

1. Filtrar productos válidos
2. Calcular precios finales
3. Mostrar resultados

### 🧩 Requisitos

- Clase `Producto`:
  - `nombre`
  - `precio`
- Usar:
  - `Predicate<Producto>` → precio mayor a 0
  - `Function<Producto, Double>` → aplicar impuesto
  - `Consumer<Double>` → imprimir precio final

### 🛠️ Condiciones

- El `Predicate` decide si el producto es válido
- El `Function` transforma el precio (ej. +18%)
- El `Consumer` imprime el resultado
- Aplicar el flujo manualmente (sin `stream()`)

### 🧠 Aprendes

- Uso combinado de Predicate + Function + Consumer
- Separación clara de responsabilidades
- Cómo se construyen pipelines sin streams
- Por qué estas interfaces existen en el JDK

## 🚀 PROYECTO 6 — Generador y verificador de tareas

### 📌 Caso real

Un backend genera tareas automáticamente y luego decide:

- Si deben ejecutarse
- Cómo mostrarlas
- Qué información extraer

### 🧩 Requisitos

- Clase `Tarea`:
  - `descripcion`
  - `prioridad`
- Usar:
  - `Supplier<Tarea>` → generar tareas
  - `Predicate<Tarea>` → validar prioridad
  - `Function<Tarea, String>` → extraer descripción
  - `Consumer<String>` → mostrar la tarea

### 🛠️ Condiciones

- El `Supplier` no recibe parámetros
- El `Predicate` filtra tareas importantes
- El `Function` transforma el objeto en texto
- El `Consumer` imprime la salida
- Ejecutar el flujo en orden lógico

### 🧠 Aprendes

- Diferenciar claramente cada interfaz funcional
- Pensar en funciones como piezas enchufables
- Diseño limpio sin `if` ni clases innecesarias
- Base sólida para entender `stream()`

---
# Referencias a Métodos

## 🚀 PROYECTO 7 — Registro de logs del sistema

### 📌 Caso real

Un backend registra mensajes de log (info, warning, error).  
El sistema primero usaba **lambdas**, pero quieres **mejorar legibilidad** usando **referencias a métodos**.

### 🧩 Requisitos

- Clase `Logger` con:
  - Método **estático** `log(String mensaje)`
- Una lista de mensajes (`List<String>`)
- Mostrar todos los mensajes usando `forEach`

### 🛠️ Condiciones

- Implementar primero el recorrido con **lambda**
- Luego reemplazar la lambda por:
  - Referencia a método estático (`Clase::metodo`)
- Usar `forEach()` directamente sobre la lista
- No usar `stream()`

### 🧠 Aprendes

- Cuándo una lambda se puede convertir en referencia a método
- Referencia a método **estático**
- Qué recibe realmente `forEach()` (un `Consumer`)
- Cómo Java interpreta `System.out::println`

## 🚀 PROYECTO 8 — Procesamiento de usuarios con métodos de instancia

### 📌 Caso real

Un sistema tiene una lista de usuarios y necesita:

- Mostrar nombres
- Ejecutar acciones propias del objeto  
  El código debe ser **limpio y expresivo**.

### 🧩 Requisitos

- Clase `Usuario`:
  - `nombre`
  - Método de instancia `mostrarNombre()`
- Lista de usuarios
- Ejecutar acciones sobre cada usuario usando `forEach`

### 🛠️ Condiciones

- Usar **método de instancia arbitraria**:
  - `Usuario::mostrarNombre`
- Comparar con la versión usando lambda
- No usar clases anónimas
- No usar `stream()`

### 🧠 Aprendes

- Referencia a método de instancia arbitraria
- Cómo Java pasa cada elemento como `this`
- Relación entre `forEach()` y `Consumer<T>`
- Por qué las referencias a métodos **mejoran la legibilidad**

---
# Creación stream(), of()

## 🚀 PROYECTO 9 — Procesamiento de nombres sin modificar la lista original

### 📌 Caso real

Un sistema tiene una **lista de nombres de usuarios** que se usa en varias partes del backend.  
Necesitas **procesarlos para mostrarlos**, pero **no debes modificar la lista original**.

### 🧩 Requisitos

- Lista de nombres (`List<String>`)
- Crear un `Stream` a partir de la lista
- Imprimir cada nombre usando `forEach()`

### 🛠️ Condiciones

- Crear el stream usando **`collection.stream()`**
- Usar `forEach()` con:
  - Lambda
  - Referencia a método
- Verificar que la lista original **permanece intacta**
- Intentar reutilizar el mismo stream y observar el error

### 🧠 Aprendes

- Qué es realmente un Stream
- Diferencia entre **almacenar** y **procesar**
- Por qué un stream **no se puede reutilizar**
- Comparación práctica entre Colecciones y Streams

## 🚀 PROYECTO 10 — Procesamiento de datos generados dinámicamente

### 📌 Caso real

Un backend necesita **procesar valores temporales** (IDs, códigos, resultados) que **no vienen de una colección**.

### 🧩 Requisitos

- Crear streams directamente desde valores
- Imprimir los elementos usando `forEach()`

### 🛠️ Condiciones

- Crear un stream usando **`Stream.of()`**
- Usar al menos:
  - Un stream de `String`
  - Un stream de `Integer`
- Probar crear un stream con `null`
- Usar la alternativa segura cuando corresponda

### 🧠 Aprendes

- Cuándo usar `Stream.of()` vs `collection.stream()`
- Que un stream **no necesita una colección**
- Riesgo real de `NullPointerException`
- Introducción a `Stream.ofNullable()`

---
# Operaciones Intermedias

## 🚀 PROYECTO 11 — Filtrado y transformación de usuarios activos

### 📌 Caso real

Un backend gestiona usuarios registrados, pero **solo algunos están activos**.  
Necesitas **filtrar**, **transformar** y **mostrar** información sin modificar la lista original.

### 🧩 Requisitos

- Clase `Usuario`:
  - `nombre`
  - `edad`
  - `activo`
- Lista de usuarios

### 🛠️ Condiciones

- Usar `stream()`
- Aplicar:
  - `filter()` → solo usuarios activos y mayores de edad
  - `map()` → convertir el usuario a su nombre en mayúsculas
- Terminar con `forEach()`
- No modificar la lista original

### 🧠 Aprendes

- Qué son operaciones intermedias
- Uso real de `filter(Predicate)`
- Transformación con `map(Function)`
- Encadenamiento lazy de streams

## 🚀 PROYECTO 12 — Procesamiento de pedidos con productos anidados

### 📌 Caso real

Un sistema de ventas maneja **pedidos**, y cada pedido contiene **una lista de productos**.  
Necesitas **procesar todos los productos como si fueran uno solo**.

### 🧩 Requisitos

- Clase `Producto`:
  - `nombre`
  - `precio`
- Clase `Pedido`:
  - `List<Producto> productos`
- Lista de pedidos

### 🛠️ Condiciones

- Usar:
  - `flatMap()` para obtener todos los productos
  - `map()` para extraer el nombre del producto
- Mostrar cada producto individualmente
- No usar bucles `for`

### 🧠 Aprendes

- Problema real que resuelve `flatMap()`
- Diferencia entre `map()` y `flatMap()`
- Aplanar estructuras anidadas
- Pensamiento funcional real

## 🚀 PROYECTO 13 — Ranking de puntuaciones únicas

### 📌 Caso real

Un videojuego genera **muchas puntuaciones**, pero:

- Puede haber duplicados
- Solo interesan las **mejores puntuaciones**
- Deben mostrarse ordenadas

### 🧩 Requisitos

- Lista de puntuaciones (`List<Integer>`)

### 🛠️ Condiciones

- Usar:
  - `distinct()` → eliminar duplicados
  - `sorted()` → ordenar de mayor a menor
  - `limit(5)` → mostrar solo el top 5
- Encadenar todas las operaciones
- Terminar con `forEach()`

### 🧠 Aprendes

- Uso combinado de operaciones intermedias
- Eliminación de duplicados con `distinct()`
- Orden natural y personalizado
- Control de cantidad con `limit()`

## 🚀 PROYECTO 14 — Limpieza y orden de datos de texto

### 📌 Caso real

Un sistema recibe **palabras duplicadas y desordenadas** desde distintas fuentes.  
Necesitas **normalizar y mostrar solo las más relevantes**.

### 🧩 Requisitos

- Lista de palabras (`List<String>`)

### 🛠️ Condiciones

- Usar:
  - `map()` → convertir todas a mayúsculas
  - `distinct()` → eliminar duplicados
  - `sorted()` → ordenar alfabéticamente
  - `limit(3)` → mostrar solo las primeras
- Usar `stream()` y `forEach()`

### 🧠 Aprendes

- Encadenamiento completo de streams
- Transformación + limpieza + orden
- Naturaleza lazy de las operaciones intermedias
- Pensar en streams como pipelines

---
# Operaciones terminales

## 🚀 PROYECTO 15 — Limpieza y reporte de nombres válidos

### 📌 Caso real

Un sistema recibe nombres de usuarios desde distintas fuentes.  
Necesitas **filtrar**, **procesar** y **obtener una lista final** para usarla en otro módulo.

### 🧩 Requisitos

- Lista de nombres (`List<String>`)
- Filtrar nombres con más de 3 caracteres
- Obtener una nueva lista con los nombres válidos

### 🛠️ Condiciones

- Usar `stream()`
- Usar una operación intermedia (`filter`)
- Usar **`collect(Collectors.toList())`** como operación terminal
- Imprimir la lista resultante
- No modificar la lista original

### 🧠 Aprendes

- Qué hace una operación terminal
- Por qué `collect()` **consume** el stream
- Conversión de `Stream` a `List`
- Diferencia entre describir el proceso y obtener el resultado

## 🚀 PROYECTO 16 — Agrupación de empleados por departamento

### 📌 Caso real

Un backend necesita **agrupar empleados por departamento** para generar reportes internos.

### 🧩 Requisitos

- Clase `Empleado`:
  - `nombre`
  - `departamento`
- Lista de empleados
- Agrupar empleados por departamento

### 🛠️ Condiciones

- Usar `stream()`
- Usar **`Collectors.groupingBy()`**
- Obtener un `Map<String, List<Empleado>>`
- Mostrar cada departamento con sus empleados

### 🧠 Aprendes

- Uso real de `collect()` para agrupar datos
- Cómo un stream termina en un `Map`
- Por qué `groupingBy` es clave en backend
- Reemplazar lógica compleja con una terminal clara

## 🚀 PROYECTO 17 — Estadísticas básicas de ventas

### 📌 Caso real

Un sistema de ventas necesita **estadísticas rápidas** sobre montos registrados.

### 🧩 Requisitos

- Lista de montos de venta (`List<Integer>`)
- Obtener:
  - Total de ventas registradas
  - Venta mínima
  - Venta máxima

### 🛠️ Condiciones

- Usar:
  - `count()`
  - `min()`
  - `max()`
- Usar `Integer::compareTo` o lambda
- Mostrar los resultados encontrados

### 🧠 Aprendes

- Operaciones terminales que devuelven valores
- Diferencia entre `count`, `min` y `max`
- Que cada operación **consume** el stream
- Uso típico de streams para métricas

## 🚀 PROYECTO 18 — Cálculo de totales y búsqueda rápida

### 📌 Caso real

Un sistema financiero necesita:

- Calcular el **total acumulado**
- Encontrar **un valor representativo** rápidamente

### 🧩 Requisitos

- Lista de números (`List<Integer>`)
- Calcular la suma total
- Obtener un número del stream para validación

### 🛠️ Condiciones

- Usar:
  - **`reduce()`** para sumar todos los valores
  - **`findFirst()`** o **`findAny()`** para obtener un elemento
- Mostrar los resultados obtenidos
- No usar bucles `for`

### 🧠 Aprendes

- Cómo `reduce()` combina todos los elementos
- Diferencia entre reducir y recolectar
- Uso práctico de `findFirst` y `findAny`
- Qué significa que el stream “muera” tras una terminal

---
# Optional creación

## 🚀 PROYECTO 19 — Búsqueda segura de usuario por email

### 📌 Caso real

Un backend necesita **buscar un usuario por email** en la base de datos.  
El usuario **puede existir o no**, y **NO se quiere usar `null`**.

### 🧩 Requisitos

- Clase `Usuario`:
  - `email`
  - `nombre`
- Clase `RepositorioUsuario`
- Método:
  - `Optional<Usuario> buscarPorEmail(String email)`

### 🛠️ Condiciones

- Simular una base de datos que **puede devolver `null`**
- Retornar el resultado usando:
  - `Optional.ofNullable(valor)`
- En el código cliente:
  - Usar `isPresent()` y `get()` **solo para entender el flujo**
  - Luego usar `ifPresent()` para mostrar el nombre
- No devolver `null` nunca

### 🧠 Aprendes

- Qué es `Optional` y por qué reemplaza a `null`
- Uso real de `Optional.ofNullable()`
- Diferencia entre **valor ausente** y **valor nulo**
- Patrón clásico de repositorio en backend

## 🚀 PROYECTO 20 — Obtención de configuración opcional del sistema

### 📌 Caso real

Un backend carga configuraciones desde variables de entorno o archivos.  
Algunas configuraciones **pueden no existir**.

### 🧩 Requisitos

- Clase `ConfiguracionService`
- Método:
  - `Optional<String> obtenerValor(String clave)`
- Simular:
  - Configuración existente
  - Configuración inexistente

### 🛠️ Condiciones

- Usar:
  - `Optional.of(valor)` cuando la clave existe
  - `Optional.empty()` cuando no existe
- En el uso:
  - Mostrar el valor solo si está presente
- No usar `null` en ningún punto

### 🧠 Aprendes

- Cuándo usar `Optional.of()`
- Cuándo usar `Optional.empty()`
- Representar explícitamente “no hay valor”
- Evitar `NullPointerException` en configuraciones reales

---

# Optional uso

## 🚀 PROYECTO 21 — Validación de sesión de usuario

### (🔹 `isPresent()` → entender por qué NO es la mejor opción)

### 📌 Caso real

En un sistema web, al iniciar una petición se revisa si el usuario tiene una **sesión activa** (token).  
El token puede venir o no desde el request.

### 🧩 Requisitos

- Recibir un `Optional<String> token`
- Verificar si el token existe
- Si existe, imprimir `"Sesión válida"`
- Si no existe, imprimir `"Sesión no iniciada"`

### 🛠️ Condiciones

- Usar **`isPresent()`**
- Usar `get()` solo para este ejercicio (sabiendo que **no es lo ideal**)
- NO usar `ifPresent()` aún

### 🧠 Aprendes

- Qué hace `isPresent()`
- Por qué **se parece demasiado a `if (x != null)`**
- Entender **qué problema intenta resolver Optional**

## 🚀 PROYECTO 22 — Envío de notificación por email

### (🔹 `ifPresent(Consumer)`)

### 📌 Caso real

Un sistema debe enviar un email **solo si el usuario tiene correo registrado**.

### 🧩 Requisitos

- Recibir `Optional<String> email`
- Si el email existe:
  - Imprimir `"Enviando correo a: <email>"`
- Si no existe:
  - No hacer nada

### 🛠️ Condiciones

- Usar **solo `ifPresent(Consumer)`**
- NO usar `isPresent()`
- NO usar `get()`

### 🧠 Aprendes

- Uso correcto de `Consumer<T>`
- Programación **declarativa**
- Eliminar `if` innecesarios
- Estilo Java moderno

## 🚀 PROYECTO 23 — Mensaje de bienvenida

### (🔹 `ifPresentOrElse(Consumer, Runnable)`)

### 📌 Caso real

En una aplicación, al entrar al sistema se muestra un saludo:

- Personalizado si el usuario está logueado
- Genérico si no lo está

### 🧩 Requisitos

- Recibir `Optional<String> nombreUsuario`
- Si existe:
  - Mostrar `"Bienvenido <nombre>"`
- Si NO existe:
  - Mostrar `"Bienvenido invitado"`

### 🛠️ Condiciones

- Usar **`ifPresentOrElse()`**
- No usar `if`, `else`, ni `get()`

### 🧠 Aprendes

- Manejar **ambos escenarios** con Optional
- Uso de `Runnable`
- Reemplazar `if-else` clásicos

## 🚀 PROYECTO 24 — Nombre visible en el perfil

### (🔹 `orElse()` vs `orElseGet()`)

### 📌 Caso real

Un perfil de usuario muestra un nombre:

- Si el usuario no definió uno → se genera un nombre por defecto (proceso costoso)

### 🧩 Requisitos

- Tener un método `generarNombrePorDefecto()` que imprima algo como `"Generando nombre..."`
- Mostrar el nombre final del perfil

### 🛠️ Condiciones

- Implementar **dos versiones**:
  1. Usando `orElse()`
  2. Usando `orElseGet()`
- Comparar cuántas veces se ejecuta `generarNombrePorDefecto()`

### 🧠 Aprendes

- Diferencia **CRÍTICA** entre `orElse` y `orElseGet`
- Cuándo usar `Supplier`
- Evitar ejecuciones innecesarias (performance backend)

## 🚀 PROYECTO 25 — Obtener usuario por email (Backend real)

### (🔹 `orElseThrow()` + 🔸 Excepción personalizada)

### 📌 Caso real

Un servicio backend busca un usuario por email en la base de datos.  
Si no existe → es un **error de negocio**.

### 🧩 Requisitos

- Simular un repositorio que retorne `Optional<Usuario>`
- Crear una excepción personalizada:
  `class UsuarioNoEncontradoException extends RuntimeException`
- Lanzar la excepción si el usuario no existe

### 🛠️ Condiciones

- Usar **`orElseThrow(() -> new ...)`**
- NO usar `if`
- NO usar `get()`

### 🧠 Aprendes

- Uso profesional de Optional en backend
- Manejo de errores de negocio
- Código limpio y expresivo
- Patrón típico **Service → Repository**

---
