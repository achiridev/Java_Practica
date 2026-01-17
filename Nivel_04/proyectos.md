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
