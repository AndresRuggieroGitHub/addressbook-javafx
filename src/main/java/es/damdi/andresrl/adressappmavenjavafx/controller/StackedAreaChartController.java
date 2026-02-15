package es.damdi.andresrl.adressappmavenjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

public class StackedAreaChartController {

    @FXML
    private Pane paneView;

    @FXML
    private void initialize() {
        loadData();
    }

    private void loadData() {
        final NumberAxis xAxis = new NumberAxis(1, 32, 1); // Rango 1-32 (para incluir 31)
        final NumberAxis yAxis = new NumberAxis();
        final StackedAreaChart<Number, Number> sac =
                new StackedAreaChart<>(xAxis, yAxis);

        sac.setTitle("Ventas Diarias por Canal");

        // Serie: Ventas en línea
        XYChart.Series<Number, Number> seriesOnline = new XYChart.Series<>();
        seriesOnline.setName("Ventas en línea");
        seriesOnline.getData().add(new XYChart.Data(1, 10));
        seriesOnline.getData().add(new XYChart.Data(3, 20));
        seriesOnline.getData().add(new XYChart.Data(6, 15));
        seriesOnline.getData().add(new XYChart.Data(9, 30));
        seriesOnline.getData().add(new XYChart.Data(12, 25));
        seriesOnline.getData().add(new XYChart.Data(15, 35));
        seriesOnline.getData().add(new XYChart.Data(18, 28));
        seriesOnline.getData().add(new XYChart.Data(21, 32));
        seriesOnline.getData().add(new XYChart.Data(24, 22));
        seriesOnline.getData().add(new XYChart.Data(27, 18));
        seriesOnline.getData().add(new XYChart.Data(30, 27));

        // Serie: Ventas en tienda
        XYChart.Series<Number, Number> seriesStore = new XYChart.Series<>();
        seriesStore.setName("Ventas en tienda");
        seriesStore.getData().add(new XYChart.Data(1, 12));
        seriesStore.getData().add(new XYChart.Data(3, 18));
        seriesStore.getData().add(new XYChart.Data(6, 9));
        seriesStore.getData().add(new XYChart.Data(9, 15));
        seriesStore.getData().add(new XYChart.Data(12, 21));
        seriesStore.getData().add(new XYChart.Data(15, 26));
        seriesStore.getData().add(new XYChart.Data(18, 19));
        seriesStore.getData().add(new XYChart.Data(21, 29));
        seriesStore.getData().add(new XYChart.Data(24, 14));
        seriesStore.getData().add(new XYChart.Data(27, 23));
        seriesStore.getData().add(new XYChart.Data(31, 17));

        sac.getData().addAll(seriesOnline, seriesStore);
        paneView.getChildren().add(sac);
    }
}
