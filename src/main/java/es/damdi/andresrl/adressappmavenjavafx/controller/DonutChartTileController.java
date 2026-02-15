package es.damdi.andresrl.adressappmavenjavafx.controller;

import es.damdi.andresrl.adressappmavenjavafx.MainApp;
import es.damdi.andresrl.adressappmavenjavafx.model.Person;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;  // Import específico para ChartData

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonutChartTileController {

    @FXML
    private Pane paneView;

    private MainApp mainApp;

    // Inyecta la instancia principal para acceder a la lista de personas
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadData();
    }

    // Método que obtiene los datos reales y crea el donut chart tile
    private void loadData() {
        paneView.getChildren().clear();
        // Agrupar las personas por generación
        Map<String, Integer> generationCounts = new HashMap<>();
        if (mainApp != null && mainApp.getPersonData() != null) {
            for (Person p : mainApp.getPersonData()) {
                String generation = getGeneration(p.getBirthday());
                generationCounts.put(generation, generationCounts.getOrDefault(generation, 0) + 1);
            }
        }
        // Preparar los datos para el chart de TilesFX
        List<ChartData> chartData = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : generationCounts.entrySet()) {
            chartData.add(new ChartData(entry.getKey(), entry.getValue()));
        }

        // Crear el Donut Chart Tile usando TilesFX
        Tile donutTile = TileBuilder.create()
                .skinType(Tile.SkinType.DONUT_CHART)
                .title("Generation Distribution")
                .chartData(chartData)
                .prefSize(400, 400)
                .animated(true) // Animación para mejor experiencia
                .textVisible(true)
                .build();

        // Agregar el tile al contenedor (paneView)
        paneView.getChildren().add(donutTile);
    }

    // Método para determinar la generación según el año de nacimiento
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