package es.damdi.andresrl.adressappmavenjavafx.controller;

import es.damdi.andresrl.adressappmavenjavafx.MainApp;
import es.damdi.andresrl.adressappmavenjavafx.model.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class GenerationsPieChartController {

    @FXML
    private Pane paneView;

    private MainApp mainApp;

    // Inyecta la instancia principal para acceder a la lista de personas
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadData();
    }

    @FXML
    private void initialize() {
        //loadData();
    }

    private void loadData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Si mainApp está inyectado, se recorre la lista real de personas
        if (mainApp != null && mainApp.getPersonData() != null) {
            Map<String, Integer> generationCounts = new HashMap<>();
            for (Person p : mainApp.getPersonData()) {
                String generation = getGeneration(p.getBirthday());
                generationCounts.put(generation, generationCounts.getOrDefault(generation, 0) + 1);
            }
            // Agregar los datos agrupados al PieChart
            for (Map.Entry<String, Integer> entry : generationCounts.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
        } else {
            // Datos por defecto si no se dispone de mainApp
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Silent Generation", 2),
                    new PieChart.Data("Baby Boomers", 3),
                    new PieChart.Data("Generation X", 4),
                    new PieChart.Data("Millennials", 5),
                    new PieChart.Data("Generation Z", 1)
            );
        }

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Generation Distribution in the Contact List");

        // Asigna colores personalizados a cada sector
        Platform.runLater(() -> {
            for (PieChart.Data data : chart.getData()) {
                switch (data.getName()) {
                    case "Silent Generation":
                        data.getNode().setStyle("-fx-pie-color: #ff9999;"); // rojo claro
                        break;
                    case "Baby Boomers":
                        data.getNode().setStyle("-fx-pie-color: #99ff99;"); // verde claro
                        break;
                    case "Generation X":
                        data.getNode().setStyle("-fx-pie-color: #9999ff;"); // azul claro
                        break;
                    case "Millennials":
                        data.getNode().setStyle("-fx-pie-color: #ffff99;"); // amarillo claro
                        break;
                    case "Generation Z":
                        data.getNode().setStyle("-fx-pie-color: #ff99ff;"); // rosa claro
                        break;
                    default:
                        data.getNode().setStyle("-fx-pie-color: #cccccc;");
                }
            }
        });

        paneView.getChildren().add(chart);
    }

    // Método para determinar la generación en función del año de nacimiento
    private String getGeneration(int birthYear) {
        if (birthYear < 1946) {
            return "Silent Generation";
        } else if (birthYear < 1965) {
            return "Baby Boomers";
        } else if (birthYear < 1981) {
            return "Generation X";
        } else if (birthYear < 1997) {
            return "Millennials";
        } else {
            return "Generation Z";
        }
    }
}