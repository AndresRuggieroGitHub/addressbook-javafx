# AddressBook JavaFX

Aplicación de ejemplo en Java que utiliza JavaFX y FXML para gestionar una libreta
de direcciones (contactos). Está pensada como proyecto didáctico y como base para
extensiones posteriores.

## Qué incluye

- Interfaz construida con JavaFX y FXML
- Ejemplos de integración con librerías como ControlsFX, TilesFX y PDFViewerFX
- Recursos: estilos CSS, ayuda y vistas FXML

## Requisitos

- JDK 21 (o la versión indicada en `pom.xml`)
- Maven (se incluye Maven Wrapper: `mvnw` / `mvnw.cmd`)

> En la mayoría de sistemas el wrapper de Maven gestiona las dependencias. Si usas
> un IDE, selecciona Java 21 como SDK del proyecto.

## Cómo compilar y ejecutar

Desde la raíz del proyecto — Windows:

```powershell
.\mvnw.cmd -DskipTests package
.\mvnw.cmd javafx:run
```

macOS / Linux:

```bash
./mvnw -DskipTests package
./mvnw javafx:run
```

Ejecución desde IDE (IntelliJ / VSCode):

1. Importa el proyecto como Maven.
2. Selecciona Java 21 como SDK del proyecto.
3. Ejecuta la clase `es.damdi.andresrl.adressappmavenjavafx.MainApp`.

## Estructura principal

- `src/main/java` — código fuente
- `src/main/resources` — FXML, CSS, imágenes y ayuda
- `pom.xml` — configuración de Maven y dependencias

## Contribuir

Si quieres proponer mejoras, abre un **issue** o envía un **pull request**. Para
trabajos más grandes, utiliza ramas `feature/xxx` y describe los cambios en la PR.

## Repositorio

https://github.com/AndresRuggieroGitHub/addressbook-javafx

## Licencia

Este proyecto está bajo la licencia MIT — ver `LICENSE`.

---

Notas opcionales que se pueden añadir más adelante: capturas de pantalla,
badges de compilación/CI (GitHub Actions) o un `CHANGELOG.md`.
