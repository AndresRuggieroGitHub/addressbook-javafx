package es.damdi.andresrl.adressappmavenjavafx.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;

public class BarChartController {
    @FXML
    private Pane paneView;

    @FXML
    private void initialize() {
        loadData();
    }

    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    private void loadData() {
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number,String> bc =
                new BarChart<>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Country");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(25601.34, austria));
        series1.getData().add(new XYChart.Data(20148.82, brazil));
        series1.getData().add(new XYChart.Data(10000, france));
        series1.getData().add(new XYChart.Data(35407.15, italy));
        series1.getData().add(new XYChart.Data(12000, usa));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(57401.85, austria));
        series2.getData().add(new XYChart.Data(41941.19, brazil));
        series2.getData().add(new XYChart.Data(45263.37, france));
        series2.getData().add(new XYChart.Data(117320.16, italy));
        series2.getData().add(new XYChart.Data(14845.27, usa));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(45000.65, austria));
        series3.getData().add(new XYChart.Data(44835.76, brazil));
        series3.getData().add(new XYChart.Data(18722.18, france));
        series3.getData().add(new XYChart.Data(17557.31, italy));
        series3.getData().add(new XYChart.Data(92633.68, usa));

        bc.getData().addAll(series1, series2, series3);
        paneView.getChildren().add(bc);

        bc.lookup(".chart-title").setStyle("-fx-text-fill: #ff6600;");
        xAxis.lookup(".axis-label").setStyle("-fx-text-fill: #0066ff;");
        yAxis.lookup(".axis-label").setStyle("-fx-text-fill: #0066ff;");
        yAxis.lookupAll(".tick-label").forEach(node -> node.setStyle("-fx-fill: blue;"));

        //Cambiar el color de las barras de la gráfica
        bc.lookupAll(".default-color0.chart-bar").forEach(n -> n.setStyle("-fx-bar-fill: purple;"));
        bc.lookupAll(".default-color1.chart-bar").forEach(n -> n.setStyle("-fx-bar-fill: yellow;"));
        bc.lookupAll(".default-color2.chart-bar").forEach(n -> n.setStyle("-fx-bar-fill: green;"));

        //Cambiar el color de la leyenda de la gráfica
        Platform.runLater(() -> {
            bc.lookupAll(".default-color0.chart-legend-item-symbol").forEach(n -> n.setStyle("-fx-background-color: purple;"));
            bc.lookupAll(".default-color1.chart-legend-item-symbol").forEach(n -> n.setStyle("-fx-background-color: yellow;"));
            bc.lookupAll(".default-color2.chart-legend-item-symbol").forEach(n -> n.setStyle("-fx-background-color: green;"));
        });
    }
}
