package es.damdi.andresrl.adressappmavenjavafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.chart.*;

public class PieChartController {

    @FXML
    private Pane paneView;

    @FXML
    private void initialize() {
        loadData();

    }

    private void loadData() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Estados Unidos", 339),
                        new PieChart.Data("India", 1426),
                        new PieChart.Data("China", 1412),
                        new PieChart.Data("Brasil", 216),
                        new PieChart.Data("Alemania", 84),
                        new PieChart.Data("Japón", 125));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Población en Millones");
        paneView.getChildren().add(chart);
    }
}
