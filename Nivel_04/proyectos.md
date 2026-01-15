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

