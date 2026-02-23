# JUnit 5

## 🚀 PROYECTO 1 — Servicio de transferencia bancaria (Testing con validaciones y excepciones)

👉 Nivel: **Intermedio**

### 📌 Caso real

Tienes una clase `TransferService` que permite transferir dinero entre cuentas.

El sistema debe:

- No permitir montos negativos
- No permitir transferencias si no hay saldo suficiente
- Descontar correctamente el saldo
- Mantener consistencia

Tu tarea es **probar que el servicio funciona correctamente**.

### 🧩 Requisitos

Clase principal:

```java
class Cuenta {  
    private double saldo;  
  
    void depositar(double monto)  
    void retirar(double monto)  
    double getSaldo()  
}
```

```java
class TransferService {  
    void transferir(Cuenta origen, Cuenta destino, double monto)  
}
```

Debes crear una clase de test:

`class TransferServiceTest`

Implementar:

- `@BeforeEach` → Crear cuentas con saldo inicial
- `@Test` → transferencia exitosa
- `@Test` → saldo insuficiente
- `@Test` → monto negativo
- `@AfterEach` → limpiar referencias

### 🛠️ Condiciones

Debes usar:

- `assertEquals` → validar saldo final
- `assertThrows` → validar excepciones
- `assertTrue` / `assertFalse`
- `assertNotNull`
- `@BeforeEach`
- `@AfterEach`

Extra:

- `@BeforeAll` para imprimir “Iniciando pruebas…”
- `@AfterAll` para imprimir “Pruebas finalizadas”

### 🧠 Aprendes

- Test de lógica de negocio
- Validación de errores
- Aislamiento entre pruebas
- Ciclo de vida real en JUnit

## 🚀 PROYECTO 2 — Validador de usuarios con múltiples reglas (Testing de reglas de negocio)

👉 Nivel: **Intermedio–Alto**

### 📌 Caso real

Un sistema valida usuarios antes de registrarlos.

Reglas:

- Username no puede ser null
- Username mínimo 4 caracteres
- Email debe contener "@"
- Edad debe ser >= 18

### 🧩 Requisitos

Clase:

```java
class UserValidator {  
    void validar(Usuario usuario)  
}
```

Debe lanzar:

- `IllegalArgumentException` si falla cualquier regla

Clase `Usuario`:

```java
String username  
String email  
int edad
```

Tu test debe:

- Verificar caso válido
- Verificar cada regla individualmente
- Verificar múltiples errores

### 🛠️ Condiciones

Debes usar:

- `assertThrows`
- `assertEquals`
- `assertTrue`
- `assertFalse`
- `assertNotNull`
- `@BeforeEach`
- `@BeforeAll`

Extra dificultad:

- Verificar el mensaje exacto de la excepción:

```java
Exception ex = assertThrows(...);  
assertEquals("Edad mínima es 18", ex.getMessage());
```

### 🧠 Aprendes

- Testing de validaciones
- Testing de mensajes de error
- Diseño de pruebas independientes
- Pruebas de reglas compuestas

