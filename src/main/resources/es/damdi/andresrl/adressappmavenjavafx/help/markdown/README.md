# Address_App

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.java.com/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-orange)](https://openjfx.io/)
[![BootstrapFX](https://img.shields.io/badge/BootstrapFX-0.4.0-%23007bff)](https://github.com/kordamp/bootstrapfx)
[![Flexjson](https://img.shields.io/badge/Flexjson-3.3-%2300cc66)](https://github.com/poiati/flexjson)
[![IntelliJ_IDEA](https://img.shields.io/badge/IntelliJ_IDEA-2023.3+-%23000000)](https://www.jetbrains.com/idea/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

![Captura de pantalla de la aplicación](/../../media/screenshot.png)


## Descripción
Este proyecto es una aplicación de escritorio para el registro y gestión de contactos. La aplicación permite visualizar una lista de contactos en una tabla, donde cada registro incluye: **nombre**, **apellido**, **calle**, **ciudad**, **código postal** y **cumpleaños**. Además, ofrece funcionalidades para crear nuevos registros, editar los existentes y borrar aquellos que ya no sean necesarios. Por último es posible analizar los datos de las generaciones de los usuarios registrados en la agenda, en forma de gráficos estadísticos.

## Características principales

- **Gestión completa de contactos**

   - Crear, editar y eliminar registros
   - Visualización en tabla interactiva
  - Visualización en forma de gráficos
   - Campos principales:
      - Nombre y apellido
      - Dirección (calle, ciudad, código postal)
      - Año de nacimiento


##   **Estadísticas visuales**
#### Gráficos con Datos cargados de la agenda
   - Gráfico de generaciones (Pie Chart)
   - Gráfico de generaciones usando **tilesfx** (Donut Chart)

#### Gráficos con datos cargados(demostrativos)
   - Población en millones de los paises mas poblados del mundo (Pie Chart)
   - Ventas diarias de una empresa (StackedArea Chart)
   - Comparativa anual (Bar Chart)
   - Seguimiento de exitencias (Line Chart)



## Contenido de la publicación
El repositorio incluye los siguientes directorios y archivos:
- **src/**: Contiene el código fuente del proyecto, desarrollado en Java utilizando Maven y JavaFX.
- **ejecutable/**: Incluye el archivo ejecutable en formato `.jar`.
- **documentación técnica/**: Documentación generada automáticamente (Javadoc, README, etc.).
- **recursos/**: Incluye manuales, guías rápidas y otros documentos (por ejemplo, archivos PDF).
- **README.md**: Archivo con información detallada sobre el proyecto.



## Desarrollo técnico
Aplicación desarrollada con Java/JavaFX gestionada con Maven, implementando persistencia de datos mediante Flexjson para serialización/deserialización en JSON.

**Stack principal:**
- **IDE**: IntelliJ IDEA 2023.3+
- **Lenguaje**: Java 21
- **Interfaz gráfica**: 
  - JavaFX 21
  - BootstrapFX 0.4.0 (Estilos modernos)
- **Gestión de dependencias**: Maven
- **Persistencia**: Flexjson 3.3

**Estructura MVC:**
- **Modelo**: Clases POJO (Person, PersonListWrapper)
- **Vista**: Archivos FXML + CSS
- **Controlador**: 8 controladores JavaFX


## Persistencia de Datos con Flexjson

- **Persistencia de datos**
   - Guardado en formato **JSON** usando **Flexjson**
   - Carga rápida desde archivo en formato **JSON**
La aplicación utiliza **Flexjson** para la serialización/deserialización avanzada de datos:

```java
public class Ejemplo {
    // Ejemplo de serialización
    String json = new JSONSerializer()
            .exclude("*.class")
            .deepSerialize(personas);

    // Ejemplo de deserialización
    PersonListWrapper wrapper = new JSONDeserializer<PersonListWrapper>()
            .deserialize(json);
}
```

## Aceleradores de Teclado
Optimiza tu flujo de trabajo con estos atajos esenciales:

**`Ctrl + N`**  →  Nueva agenda  :  Archivo  →  Nuevo

**`Ctrl + O`**  →  Abrir archivo  :  Archivo  →  Abrir

**`Ctrl + S`**  →  Guardar cambios  :  Archivo  →  Guardar

**`Ctrl + A`**  →  Guardar como...  :  Archivo  →  Guardar como

**`Ctrl + Q`**  →  Salir de la aplicación  :  Archivo  →  Salir

>  **Nota**: Los atajos funcionan en Windows/Linux. En macOS usa `⌘` en lugar de `Ctrl`



## Despliegue
#### Ejecutar desde IntelliJ:
Run → Edit Configurations → VM Options: --module-path "lib/javafx-sdk-21" --add-modules javafx.controls,javafx.fxml

### Windows
1. Navega al directorio `ejecutable` donde se encuentra el archivo `.jar`.
2. Haz doble clic, o haz clic derecho y selecciona "Ejecutar" para abrir la aplicación.

### Línea de comandos
También puedes ejecutar la aplicación desde la línea de comandos:
1. Abre un terminal o línea de comandos.
2. Navega al directorio donde se encuentra el archivo `.jar`.
3. Ejecuta el siguiente comando:
   ```bash
   java -jar adressAppMavenJavaFX.jar
   ```
   





