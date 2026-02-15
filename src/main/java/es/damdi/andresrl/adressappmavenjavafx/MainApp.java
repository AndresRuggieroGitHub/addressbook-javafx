package es.damdi.andresrl.adressappmavenjavafx;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import es.damdi.andresrl.adressappmavenjavafx.controller.DonutChartTileController;
import es.damdi.andresrl.adressappmavenjavafx.controller.GenerationsPieChartController;
import es.damdi.andresrl.adressappmavenjavafx.controller.PersonEditDialogController;
import es.damdi.andresrl.adressappmavenjavafx.controller.PersonOverviewController;
import es.damdi.andresrl.adressappmavenjavafx.model.Person;
import es.damdi.andresrl.adressappmavenjavafx.model.PersonListWrapper;
import es.damdi.andresrl.adressappmavenjavafx.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kordamp.bootstrapfx.BootstrapFX;


public class MainApp extends Application {

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private static BorderPane rootLayout;

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp Andrés Ruggiero Lujan");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/icon.png")));

        initRootLayout();

        showPersonOverview();
    }


    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("css/modena.css");
            //scene.getStylesheets().add("css/modena_dark.css");
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            //scene.getStylesheets().add("css/modena.css");
            //scene.getStylesheets().add("css/modena_dark.css");
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/icon.png")));


            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //APARTADO 5
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            // Crear un objeto File con la ruta del archivo
            File file = new File(filePath);
            // Comprobar si el archivo existe
            if(file.exists()) {
                return file;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
        public void loadPersonDataFromFile(File file) {
        try (FileReader reader = new FileReader(file)) {
            char[] buffer = new char[(int) file.length()];
            reader.read(buffer);
            String json = new String(buffer);

            PersonListWrapper wrapper = PersonListWrapper.fromJson(json);
            personData.clear();
            personData.addAll(wrapper.getPersons());

            setPersonFilePath(file);
        } catch (IOException e) {
            showErrorNotification("Error al cargar: " + e.getMessage());
        } catch (Exception e) {
            showErrorNotification("Error en el formato del JSON: " + e.getMessage());
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            System.out.println("Número de personas a guardar: " + personData.size()); // Debug
            String json = wrapper.toJson();
            System.out.println("JSON generado: " + json); // Debug

            writer.write(json);
            setPersonFilePath(file);

        } catch (IOException e) { // catches file-related exceptions
            showErrorNotification("Could not load data from file:\n" + file.getPath());
        }
    }

    private void showErrorNotification(String message) {
        Notifications.create()
                .title("Error")
                .text(message)
                .hideAfter(Duration.seconds(5))
                .showError();
    }



    // Graficos
    public void showPieChart() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/PieChart.fxml"));
            AnchorPane initCenter = (AnchorPane) loader.load();
            // Set initCenter into the center of root layout.
            rootLayout.setCenter(initCenter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStackedAreaChart() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/StackedAreaChart.fxml"));
            AnchorPane initCenter = (AnchorPane) loader.load();
            // Set initCenter into the center of root layout.
            rootLayout.setCenter(initCenter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBarChart() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/BarChart.fxml"));
            AnchorPane barChartPane = (AnchorPane) loader.load();
            // Creamos una ventana modal donde mostramos la gráfica
            Stage barChartStage = new Stage();
            barChartStage.setTitle("Bar Chart Andrés Ruggiero");
            barChartStage.initModality(Modality.WINDOW_MODAL);
            barChartStage.initOwner(primaryStage);
            barChartStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/icon_world.png")));

            Scene scene = new Scene(barChartPane, 600, 425);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            barChartStage.setScene(scene);

            barChartStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //estadisticas cumpleaños
    public static void showLineChart() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LineChart.fxml"));
            AnchorPane initCenter = (AnchorPane) loader.load();
            // Set initCenter
            rootLayout.setCenter(initCenter);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void showMarkdown() {

        String markdown = loadMarkdown();


        // Convertir Markdown a HTML utilizando Flexmark
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        String htmlContent = renderer.render(document);

        // Crear un WebView para mostrar el contenido HTML generado
        WebView webView = new WebView();
        webView.getEngine().loadContent(htmlContent);

        Stage markdownStage = new Stage();
        markdownStage.setTitle("Markdown Andrés Ruggiero");
        markdownStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/iconmarkdown.png")));

        Scene scene = new Scene(webView, 600, 425);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        markdownStage.setScene(scene);

        markdownStage.show();
    }
    private String loadMarkdown(){
        // Obtener la URL del archivo Markdown en resources
        URL resourceUrl = getClass().getResource("help/markdown/README.md");

        if (resourceUrl == null) {
            System.err.println("⚠️ El archivo Markdown no se encontró en el classpath.");
            return "Error: No se pudo encontrar el archivo Markdown.";
        }

        try {
            // Convertir la URL en una ruta válida para leer el archivo
            Path path = Paths.get(resourceUrl.toURI());
            return Files.readString(path); // Método más eficiente que readAllBytes()
        } catch (IOException | URISyntaxException e) {
            System.err.println("❌ Error al cargar el archivo Markdown: " + e.getMessage());
            return "Error al cargar el archivo Markdown.";
        }
    }

    public void showPDF() {
        // Crear el visor PDF
        PDFDisplayer displayer = new PDFDisplayer();
        Scene scene = new Scene(displayer.toNode());

        // Obtener la URL del PDF
        URL pdfUrl = getClass().getResource("help/pdf/ayuda.pdf");


        if (pdfUrl == null) {
            System.err.println("⚠️ El archivo PDF no se encontró en el classpath.");
            return;
        }
        try {
            // Convertir la URL en un archivo y cargar el PDF
            File pdfFile = new File(pdfUrl.toURI());
            displayer.loadPDF(pdfFile);
        } catch (URISyntaxException | IOException e) {
            System.err.println("❌ Error al cargar el PDF: " + e.getMessage());
            return;
        }
        // Configurar y mostrar la ventana
        Stage pdfStage = new Stage();
        pdfStage.setTitle("Ayuda en PDF");
        pdfStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/iconpdf.png")));

        pdfStage.setScene(scene);

        pdfStage.show();
    }

    public void showtHTML() {
        // Crear un componente WebView para mostrar el contenido HTML
        WebView webView = new WebView();

        // Obtener la URL del archivo HTML dentro de los recursos
        URL url = getClass().getResource("help/html/index.html");

        if (url != null) {
            // Cargar el archivo HTML en el WebView
            webView.getEngine().load(url.toExternalForm());
        } else {
            System.err.println("⚠️ No se encontró el archivo HTML en el classpath.");
            webView.getEngine().loadContent("<html><body><h2>Error: No se pudo cargar la ayuda.</h2></body></html>");
        }

        // Configurar la escena y mostrar la ventana de ayuda
        Stage htmlStage = new Stage();
        htmlStage.setTitle("Manual de Usuario");
        htmlStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/iconhtml.png")));

        Scene scene = new Scene(webView, 600, 425);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        htmlStage.setScene(scene);

        htmlStage.show();
    }

    public void showGenerationsPieChart() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/GenerationsPieChart.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crear la ventana modal para mostrar el gráfico
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Generation Pie Chart");
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/icon_generations.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            dialogStage.setScene(scene);

            // Inyecta la referencia a MainApp en el controlador para acceder a la lista de personas
            GenerationsPieChartController controller = loader.getController();
            controller.setMainApp(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDonutChartTile() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/DonutChartTile.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crear la ventana modal para mostrar el donut chart tile
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Generation Donut Chart Tile");
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/media/icon_generations.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            // Puedes añadir estilos si usas BootstrapFX u otro CSS
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            dialogStage.setScene(scene);

            // Inyectar la instancia de MainApp en el controlador para usar los datos reales
            DonutChartTileController controller = loader.getController();
            controller.setMainApp(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        launch(args);
        Person p = new Person("Andrés", "Ruggiero");
        System.out.println(p.toMap());
    }
}