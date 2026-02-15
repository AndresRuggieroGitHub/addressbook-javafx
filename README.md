# 📒 AddressBook JavaFX

Aplicación desarrollada en **Java 21** utilizando **JavaFX** y **FXML** para gestionar una libreta de direcciones (contactos). Está pensada como proyecto didáctico y como base para
extensiones posteriores.


## 🚀 Características

- Interfaz gráfica desarrollada con **JavaFX + FXML**
- Arquitectura basada en Maven
- Integración con librerías externas:
  - ControlsFX
  - TilesFX
  - PDFViewerFX
- Estilos personalizados mediante CSS
- Separación entre lógica, vista y recursos


## 🛠️ Tecnologías utilizadas

- Java 21  
- JavaFX  
- Maven (incluye Maven Wrapper)  
- FXML  
- CSS  


## 📦 Requisitos

- JDK 21 (o la versión indicada en `pom.xml`)
- No es necesario instalar Maven manualmente (incluye `mvnw` / `mvnw.cmd`)

> ⚠️ Si usa un IDE, asegurarse de configurar Java 21 como SDK del proyecto.


## ▶️ Compilación y ejecución

### Windows

```powershell
.\mvnw.cmd clean package
.\mvnw.cmd javafx:run
```

### macOS / Linux

```bash
./mvnw clean package
./mvnw javafx:run
```


## 💻 Ejecución desde IDE (IntelliJ / VSCode)

1. Importar el proyecto como **Maven Project**
2. Configurar **Java 21** como SDK
3. Ejecutar la clase:

```
es.damdi.andresrl.adressappmavenjavafx.MainApp
```


## 📂 Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/        → Código fuente
 │   └── resources/   → FXML, CSS, imágenes y ayuda
pom.xml               → Configuración y dependencias Maven
```


## 📄 Licencia

Este proyecto está bajo la licencia MIT — ver `LICENSE`.
