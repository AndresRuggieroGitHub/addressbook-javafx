# adressAppMavenJavaFX

Pequeña aplicación JavaFX de ejemplo (Address Book) creada con Maven.

## Descripción

Proyecto de escritorio en Java que demuestra uso de JavaFX, FXML y algunas librerías auxiliares (ControlsFX, TilesFX, BootstrapFX, PDFViewerFX, etc.).

## Requisitos

- JDK 21 (o la versión configurada en `pom.xml`).
- Maven (se incluye Maven Wrapper `mvnw` y `mvnw.cmd`).

## Compilar y ejecutar

En Windows (desde la raíz del proyecto):

```powershell
.\mvnw.cmd -DskipTests package
.\mvnw.cmd javafx:run
```

En Linux / macOS:

```bash
./mvnw -DskipTests package
./mvnw javafx:run
```

Si usas un IDE (IntelliJ/VSCode) asegúrate de usar un JDK compatible y configurar VM options para JavaFX si es necesario.

## Estructura

- `src/main/java`: código fuente Java
- `src/main/resources`: fxml, CSS y recursos
- `pom.xml`: configuración de Maven

## Subir a GitHub

1. Crea un repositorio vacío en GitHub (por ejemplo `adressAppMavenJavaFX`).
2. Añade el remoto y empuja:

```bash
git remote add origin https://github.com/<usuario>/<repo>.git
git branch -M main
git push -u origin main
```

Si tienes la CLI de GitHub (`gh`) instalada puedes crear y empujar en un paso:

```bash
gh repo create <usuario>/<repo> --public --source=. --remote=origin --push
```

## Licencia

Este repositorio incluye una licencia MIT en `LICENSE`.

---

Si quieres, puedo crear la repo en GitHub por ti (necesitaré tu autorización/token), o puedo ejecutar el `git push` si ya creaste el repo remoto. ¿Prefieres que lo haga ahora?
