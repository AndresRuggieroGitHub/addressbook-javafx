# adressAppMavenJavaFX

Pequeña aplicación JavaFX de ejemplo (Address Book) creada con Maven.

## Descripción

Proyecto de escritorio en Java que demuestra uso de JavaFX, FXML y algunas librerías auxiliares (ControlsFX, TilesFX, BootstrapFX, PDFViewerFX, etc.).

# addressbook-javafx

Address Book JavaFX — aplicación de ejemplo creada con Maven.

## Descripción

Aplicación de escritorio en Java que demuestra el uso de JavaFX y FXML para gestionar una lista de contactos. Incluye ejemplos de integración con librerías como ControlsFX, TilesFX y PDFViewerFX.

## Requisitos

- JDK 21 (o la versión indicada en `pom.xml`)
- Maven (se incluye Maven Wrapper `mvnw` / `mvnw.cmd`)

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

Si usas un IDE (IntelliJ/VSCode) asegúrate de seleccionar el JDK correcto y, si es necesario, configurar las VM options para JavaFX.

## Estructura del proyecto

- `src/main/java` — código fuente
- `src/main/resources` — FXML, CSS y recursos (imágenes, help, etc.)
- `pom.xml` — configuración de Maven y dependencias

## Repositorio

Repositorio en GitHub: https://github.com/AndresRuggieroGitHub/addressbook-javafx

## Licencia

Proyecto con licencia MIT (ver `LICENSE`).

---

Si quieres que haga algún cambio adicional al `README` (más capturas, instrucciones de desarrollo o badges), dímelo y lo actualizo.
Este repositorio incluye una licencia MIT en `LICENSE`.
