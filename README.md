# SharedTasks

App móvil para iOS y Android con la que una pareja reparte las tareas del hogar que tienen fecha límite, como la ITV, el seguro o la revisión de la caldera, y elige quién se encarga de cada una. La app avisa a tiempo (30 días antes, 7 días antes y el día límite) y, si se pasa la fecha, también avisa a la pareja para que lo resuelvan entre los dos.

Está hecha con Kotlin Multiplatform y Compose Multiplatform, con Firebase como backend (Auth, Firestore, Remote Config, Crashlytics y Messaging).

## Requisitos

- Android Studio
- JDK
- Xcode (solo en Mac, necesario para compilar la app de iOS)

## Archivos de Firebase (no están en el repo)

Los archivos de configuración de Firebase contienen claves de los proyectos, así que están en el `.gitignore` y **no se suben al repositorio**. Al clonar el proyecto en una máquina nueva hay que conseguirlos y colocarlos a mano. Sin ellos la app no compila o se cierra al arrancar.

Hay dos entornos, cada uno con su propio proyecto de Firebase:

| Entorno | Proyecto de Firebase | Android | iOS |
| --- | --- | --- | --- |
| Desarrollo | `shared-tasks-dev` | `androidApp/src/dev/google-services.json` | `iosApp/Firebase/Dev/GoogleService-Info.plist` |
| Producción | `shared-tasks-prod` | `androidApp/src/prod/google-services.json` | `iosApp/Firebase/Prod/GoogleService-Info.plist` |

### Cómo conseguirlos

1. Entra en la [consola de Firebase](https://console.firebase.google.com) y abre el proyecto del entorno.
2. Pulsa la rueda dentada junto a «Descripción general del proyecto» y entra en **Configuración del proyecto**.
3. En la pestaña **General**, baja hasta «Tus apps».
4. En la app de Android, pulsa **Descargar google-services.json**.
5. En la app de iOS, pulsa **Descargar GoogleService-Info.plist**.
6. Copia cada archivo a su ruta de la tabla, respetando el nombre exacto.

Repite el proceso con el otro proyecto.

### Qué app corresponde a cada entorno

| | Desarrollo | Producción |
| --- | --- | --- |
| Android | `com.antonio.sharedtasks.dev` | `com.antonio.sharedtasks` |
| iOS | `com.antonio.sharedtasks.SharedTasks.dev` | `com.antonio.sharedtasks.SharedTasks` |

Gracias a esto, las dos versiones pueden estar instaladas a la vez en el mismo móvil.

## Cómo ejecutar

### Android

Elige la variante de compilación (`devDebug` o `prodDebug`) en Android Studio, o usa Gradle:

```
./gradlew :androidApp:assembleDevDebug
./gradlew :androidApp:assembleProdDebug
```

### iOS

Abre la carpeta `iosApp` en Xcode y selecciona el esquema `iosApp-Dev` o `iosApp-Prod`.

No elijas un equipo de desarrollo (team) desde Android Studio: cambiaría el Bundle ID y la app dejaría de coincidir con la registrada en Firebase.

### Comprobar que funciona

Si arranca sin cerrarse y los servicios de Firebase responden en el entorno elegido, la configuración es correcta. Si se cierra al arrancar, lo más probable es que falte alguno de los archivos de la sección anterior.

## Estructura del proyecto

- `domain`: reglas del negocio, sin dependencias de plataforma.
- `data`: acceso a datos (Firebase) y repositorios.
- `presentation`: pantallas y lógica de presentación.
- `shared`: punto de entrada común a Android e iOS con Compose Multiplatform.
- `androidApp`: aplicación Android, con los flavors `dev` y `prod`.
- `iosApp`: aplicación iOS, con las configuraciones y esquemas `Dev` y `Prod`.
- `architecture-test`: pruebas que comprueban que se respeta la arquitectura.

## Limitaciones conocidas

- Las notificaciones push en iOS necesitan una cuenta de Apple Developer de pago (APNs), así que de momento solo funcionan en Android.