# Jerarquía de Excepciones

## 🚀 PROYECTO 1 — Sistema de monitoreo de fallos en una aplicación bancaria

### (Jerarquía `Throwable → Error vs Exception`)

### 📌 Caso real

Estás trabajando en un **sistema bancario**.  
Tu equipo quiere un módulo que registre **fallos críticos del sistema** y **errores recuperables de la aplicación** para debugging.

### 🧩 Requisitos

- Crear una clase `MonitorErroresBancarios`
- Simular:
  - Un error crítico del sistema (`OutOfMemoryError`)
  - Un error de la aplicación (`NullPointerException`)
- Imprimir:
  - El nombre de la excepción
  - Si es `Error` o `Exception`
  - Si hereda de `Throwable`

### 🛠️ Condiciones

- Usar `instanceof Throwable`, `instanceof Error`, `instanceof Exception`
- NO capturar `Error` con `try-catch` (solo simular con un método que lo lance)
- Capturar la `Exception` con `try-catch`

### 🧠 Aprendes

- Jerarquía real de excepciones
- Diferencia entre fallos **de la JVM** y fallos **del software**
- Por qué `Error` no se maneja normalmente en aplicaciones

## 🚀 PROYECTO 2 — Sistema de login corporativo (Checked Exception simulada)

### (🔸 Checked Exceptions obligatorias SIN archivos)

### 📌 Caso real

Estás creando un **sistema corporativo de login interno**.  
Si el servidor de autenticación está caído, debes **notificar al sistema superior**.

### 🧩 Requisitos

- Crear una excepción checked personalizada:
  `class ServidorAutenticacionException extends Exception {}`
- Crear método:
  `public void autenticarUsuario(String user, String pass) throws ServidorAutenticacionException`
- Simular que el servidor falla lanzando la excepción

### 🛠️ Condiciones

- El método que llama a `autenticarUsuario` DEBE:
  - Capturar la excepción con `try-catch`
  - O declararla con `throws`
- Crear un flujo con:
  - `loginController()` → `authService()` → `autenticarUsuario()`

### 🧠 Aprendes

- Qué es una Checked Exception sin usar archivos
- Cómo se propaga una excepción entre capas
- Por qué Java obliga a manejar errores externos

## 🚀 PROYECTO 3 — Validación de datos en registro de usuarios

### (🔸 Unchecked Exceptions / RuntimeException)

### 📌 Caso real

Estás desarrollando un **sistema de registro de usuarios** para una app.  
El backend confía en que los desarrolladores frontend envíen datos correctos.

### 🧩 Requisitos

- Crear una clase `UsuarioService`
- Método:
    `public void registrarUsuario(String nombre, int edad)`
- Si la edad es menor que 0 o mayor que 150, lanzar `IllegalArgumentException`

### 🛠️ Condiciones

- Lanzar la excepción manualmente con `throw`
- NO usar `throws`
- NO usar `try-catch` obligatorio

### 🧠 Aprendes

- Qué son RuntimeExceptions
- Errores de lógica del programador
- Por qué Java no obliga a capturarlos

## 🚀 PROYECTO 4 — Microservicio de pagos (RuntimeException vs Exception)

### (Decidir qué tipo de excepción usar)

### 📌 Caso real

Estás creando un **microservicio de pagos (como Stripe o MercadoPago)**.  
Hay dos tipos de errores:

1. Error del programador (monto inválido)
2. Error externo (fallo en sistema de pagos externo)

### 🧩 Requisitos

- Crear método:
  `public void validarMonto(double monto)`
  - Si monto < 0 → lanzar `IllegalArgumentException` (Runtime)
- Crear método:
  `public void conectarPasarelaPago() throws PasarelaPagoException`
  - Simular fallo lanzando una Checked Exception personalizada
- Crear método:
  `public void procesarPago(double monto)`
  - Llamar a ambos métodos

### 🛠️ Condiciones

- `validarMonto` NO debe declarar `throws`
- `conectarPasarelaPago` SÍ debe declarar `throws`
- `procesarPago` debe capturar la excepción externa con `try-catch`

### 🧠 Aprendes

- Responsabilidad del error
- Diseño profesional de APIs
- Diferencia REAL entre Checked vs Runtime
- Arquitectura backend (validación → infraestructura)

---

# Crear tus propias excepciones

## 🚀 PROYECTO 5 — Sistema de usuarios en una plataforma educativa

### (Excepción personalizada con significado de negocio)

### 📌 Caso real

Estás desarrollando el backend de una **plataforma educativa tipo Coursera o Platzi**.  
Cuando un profesor intenta acceder a los datos de un alumno por ID, el sistema debe lanzar un error claro si el alumno no existe.

El equipo quiere **evitar RuntimeException genéricas** y usar excepciones con **significado de negocio**.

### 🧩 Requisitos

- Crear una excepción personalizada:
    `class AlumnoNoEncontradoException extends RuntimeException`
- Crear un servicio:
    `public Alumno obtenerAlumnoPorId(int id)`
- Si el alumno no existe → lanzar `AlumnoNoEncontradoException(id)`
- Simular una lista interna de alumnos (sin base de datos)

### 🛠️ Condiciones

- La excepción debe tener:
  - Un constructor con mensaje
  - Un constructor que reciba el `id` y genere el mensaje automáticamente
- El método NO debe retornar `null`
- Debe usar `throw new AlumnoNoEncontradoException(...)`

### 🧠 Aprendes

- Por qué usar excepciones personalizadas en lugar de `RuntimeException`
- Cómo dar **significado de negocio** a los errores
- Evitar `null` como señal de error
- Diseño típico de servicios backend

---

# IO y NIO.2

## 🚀 PROYECTO 6 — Gestor de configuración de una aplicación backend

### (Path, Paths, Files.readString(), Files.writeString(), Files.exists())

### 📌 Caso real

Estás desarrollando un **backend de una aplicación web**.  
El sistema guarda su configuración en un archivo de texto (`config.txt`), por ejemplo:

```
db.url=localhost
db.user=admin
db.password=1234
```

Si el archivo no existe, el sistema debe crear uno con valores por defecto.

### 🧩 Requisitos

- Crear un archivo `config.txt` usando `Files.writeString()`
- Verificar si el archivo existe con `Files.exists()`
- Si existe, leerlo con `Files.readString()`
- Imprimir el contenido de la configuración en consola

### 🛠️ Condiciones

- Usar `Path` y `Paths.get("config.txt")`
- NO usar `File` ni `FileReader`
- Si no existe el archivo, crear uno con configuración por defecto

### 🧠 Aprendes

- Cómo trabajar con archivos modernos en Java
- Diferencia entre `Path` y `String`
- Lectura y escritura simple de archivos
- Uso real de archivos en backend (configuración)

## 🚀 PROYECTO 7 — Analizador de logs de una aplicación

### (Files.lines() + Streams)

### 📌 Caso real

Estás trabajando en un **sistema que genera logs** como:

```
INFO Usuario conectado
ERROR Fallo en base de datos
INFO Usuario desconectado
ERROR Timeout en servidor
```

Tu tarea es crear un **analizador de logs** que cuente cuántos errores existen.

### 🧩 Requisitos

- Crear un archivo `app.log` con varias líneas (usando `Files.writeString`)
- Leer el archivo usando `Files.lines()`
- Contar cuántas líneas contienen `"ERROR"`
- Imprimir el número total de errores

### 🛠️ Condiciones

- `Files.lines()` devuelve un **Stream<String>**, debes usar Stream API (`filter`, `count`)
- NO leer línea por línea manualmente
- Usar `Path` y `Paths`

### 🧠 Aprendes

- Integrar **NIO.2 + Streams**
- Procesamiento moderno de archivos
- Casos reales de observabilidad y logging
- Programación funcional en Java

## 🚀 PROYECTO 8 — Sistema de respaldo (backup) de archivos

### (Files.copy())

### 📌 Caso real

En una empresa, antes de actualizar una aplicación, se hace un **backup automático** del archivo de datos.  
Tu tarea es copiar un archivo de datos a una carpeta de respaldo.

### 🧩 Requisitos

- Crear un archivo `datos.txt` con contenido de prueba
- Copiarlo a `backup/datos_backup.txt` usando `Files.copy()`
- Verificar si el archivo original existe antes de copiarlo
- Mostrar un mensaje `"Backup completado"`

### 🛠️ Condiciones

- Usar `Path origen = Paths.get("datos.txt")`
- Usar `Path destino = Paths.get("backup/datos_backup.txt")`
- Crear la carpeta `backup` manualmente o desde el sistema operativo
- NO usar librerías externas ni `File`

### 🧠 Aprendes

- Copia de archivos con NIO.2
- Automatización de backups (muy real en backend)
- Manejo de rutas multiplataforma
- Operaciones críticas de sistemas

## 🚀 PROYECTO 9 — Gestor de perfiles de usuario en una aplicación de escritorio

### (Path, Files.readString, Files.writeString, Files.exists, diseño real)

### 📌 Caso real

Estás desarrollando una **aplicación de escritorio tipo launcher (Minecraft Launcher, Steam, etc.)**.  
Cada usuario tiene un archivo de perfil guardado en disco, por ejemplo:

`perfiles/daniel.profile`

Contenido del archivo:

```
nombre=Daniel
tema=dark
idioma=es
```

Cuando el usuario inicia la app, el sistema debe:

- Leer su perfil si existe
- Crear uno por defecto si no existe

### 🧩 Requisitos

- Crear una carpeta `perfiles/`
- Recibir un nombre de usuario (ej. `"daniel"`)
- Construir dinámicamente la ruta del archivo con `Paths.get("perfiles", usuario + ".profile")`
- Si el archivo no existe:
  - Crearlo con configuración por defecto usando `Files.writeString()`
- Si existe:
  - Leerlo con `Files.readString()` y mostrarlo

### 🛠️ Condiciones

- Usar `Path` para todas las rutas
- Usar `Files.exists()` antes de leer
- No usar rutas hardcodeadas tipo `"C:/..."`
- Simular múltiples usuarios llamando al método con diferentes nombres

### 🧠 Aprendes

- Rutas dinámicas (muy real en apps)
- Gestión de archivos por usuario
- Persistencia simple sin base de datos
- Buenas prácticas de rutas multiplataforma

## 🚀 PROYECTO 10 — Sistema de historial y rotación de logs de una aplicación backend

### (NIO.2 completo + Streams + lógica real de sistema)

### 📌 Caso real

Estás desarrollando un **microservicio backend** que genera logs de actividad de usuarios.  
Por políticas de la empresa:

- Los logs no pueden crecer infinitamente
- Cuando el archivo supera cierto tamaño (simulado por número de líneas), se debe:
    1. Crear un backup del log
    2. Limpiar el log principal
    3. Registrar que se hizo una rotación

Este patrón se llama **log rotation** y es usado en Linux, servidores web, Docker, etc.

### 🧩 Requisitos

- Crear un archivo `logs/app.log` con varias líneas (simula actividad)
- Leer el archivo con `Files.lines()`
- Contar el número de líneas
- Si las líneas son mayores a 5:
  - Copiar el archivo a `logs/backup/app_<timestamp>.log` usando `Files.copy()`
  - Vaciar el archivo original con `Files.writeString()` (contenido vacío)
- Mostrar mensajes como:
  - `"Rotación de logs completada"`
  - `"No es necesario rotar logs"`

### 🛠️ Condiciones

- Usar rutas con:

```
  Path logPath = Paths.get("logs", "app.log");
  Path backupPath = Paths.get("logs", "backup", "app_" + System.currentTimeMillis() + ".log");
```

- Usar:
  - `Files.exists()`
  - `Files.readString()` o `Files.lines()`
  - `Files.copy()`
  - `Files.writeString()`
- No usar `java.io.File`
- No usar librerías externas
- Crear manualmente las carpetas `logs/` y `logs/backup/` (o documentarlo)

### 🧠 Aprendes

- Log rotation (concepto real de sistemas operativos y backend)
- Automatización de mantenimiento de archivos
- Uso combinado de NIO.2 + Streams
- Manejo de timestamps en rutas
- Diseño de tareas de infraestructura (muy valorado en empresas)

### 🔹 Extra 1: Servicio dedicado

Crear una clase:

`class LogRotationService`

con métodos:

- `void escribirLog(String mensaje)`
- `void rotarSiEsNecesario()`

### 🔹 Extra 2: Configuración externa

Guardar el límite de líneas en un archivo:

`config/log_config.txt MAX_LINES=5`

Leerlo con `Files.readString()`.

### 🔹 Extra 3: Excepciones personalizadas

Crear:

`class LogRotationException extends RuntimeException`

y lanzar si falla la copia del archivo.

---
