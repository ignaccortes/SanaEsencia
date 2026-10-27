# SanaEsencia 

**Aplicación móvil de bienestar integral**

## 1. Descripción del proyecto

SanaEsencia es una aplicación móvil desarrollada para Android que busca ofrecer una experiencia enfocada en el bienestar integral. Su interfaz presenta una pantalla de bienvenida, acceso y registro de usuarios, una pantalla principal y un perfil personal.

El proyecto se desarrolla como parte de un trabajo académico de la carrera de Desarrollo de Aplicaciones Móviles.

## 2. Integrantes del equipo

- **[Diego Cortés]**
- **[Ignacio Cortés]**
- **[Matias Posada]**

**Número de equipo:** [8]

## 3. Tecnologías utilizadas

- **Lenguaje:** Kotlin.
- **Entorno de desarrollo:** Android Studio.
- **Interfaz de usuario:** Jetpack Compose.
- **Componentes visuales:** Material 3.
- **Persistencia local:** Room sobre SQLite.
- **Navegación:** Navigation Compose.
- **Gestión de estado:** ViewModel y StateFlow.
- **Control de versiones:** Git y GitHub.

## 4. Funcionalidades implementadas

- Pantalla de bienvenida con opciones de registro e inicio de sesión.
- Registro de usuarios con validaciones de campos y contraseña.
- Inicio de sesión con validación de credenciales.
- Persistencia local de usuarios mediante Room y SQLite.
- Navegación entre las pantallas principales.
- Pantalla de inicio con saludo personalizado y tarjetas de acceso.
- Perfil de usuario con visualización de datos personales.
- Selección o captura de fotografía de perfil mediante las funciones de cámara del dispositivo.
- Persistencia local de la fotografía de perfil y visualización en Home.
- Componentes reutilizables y animaciones de interfaz.


## 5. Requisitos previos

Para abrir y ejecutar el proyecto se necesita:

- Android Studio compatible con el proyecto.
- JDK compatible con la configuración de Gradle.
- Android SDK correspondiente a la configuración del proyecto.
- Conexión a Internet durante la primera sincronización de Gradle y la descarga de dependencias.
- Emulador Android o dispositivo físico para ejecutar la aplicación.

**Configuración Android del proyecto:**

- `minSdk`: 26.
- `compileSdk`: 37.
- `targetSdk`: 37.
- `versionName`: 1.0.

## 6. Instalación y ejecución

### 6.1. Clonar el repositorio

Desde una terminal, ejecutar:

```bash
git clone https://github.com/ignaccortes/SanaEsencia.git
```

Ingresar a la carpeta del proyecto:

```bash
cd SanaEsencia
```

### 6.2. Abrir el proyecto

1. Abrir Android Studio.
2. Seleccionar **Open**.
3. Elegir la carpeta raíz `SanaEsencia` que se acaba de clonar.
4. Esperar a que termine la sincronización de Gradle.
5. Resolver cualquier requisito o dependencia que solicite el entorno.

### 6.3. Ejecutar la aplicación

1. Seleccionar un emulador Android o conectar un dispositivo físico con depuración USB habilitada.
2. Seleccionar la configuración de ejecución de la aplicación.
3. Pulsar **Run ▶**.
4. Esperar a que Android Studio compile, instale y ejecute SanaEsencia.

### 6.4. Generar un APK de prueba

Desde Android Studio, utilizar la opción:

**Build → Build Bundle(s) / APK(s) → Build APK(s)**

El APK de depuración normalmente se genera en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Este archivo permite instalar una versión de prueba en dispositivos Android compatibles.

## 7. Estructura general del proyecto

```text
SanaEsencia/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/cl/sanaesencia/app/
│   │       │   ├── data/
│   │       │   ├── navigation/
│   │       │   ├── ui/
│   │       │   └── viewmodels/
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

La estructura representa la organización general del proyecto. Los paquetes y archivos concretos pueden variar según la versión final del repositorio.

## 8. Persistencia y datos

La aplicación utiliza almacenamiento local para gestionar los datos de usuario mediante Room y SQLite. Los datos locales de cada instalación se mantienen en el dispositivo correspondiente; no se sincronizan automáticamente entre teléfonos.

La fotografía de perfil se guarda mediante el mecanismo de persistencia local implementado en la aplicación.

## 9. Repositorio

Código fuente: [SanaEsencia en GitHub](https://github.com/ignaccortes/SanaEsencia)

## 10. Estado del proyecto

Proyecto académico en etapa de prototipo. Las funcionalidades descritas corresponden al alcance implementado y deben verificarse en la versión entregada.

## 11. Licencia

Proyecto desarrollado con fines académicos. Los términos de uso y distribución quedan sujetos a las indicaciones del equipo docente y a las licencias de los componentes utilizados.
