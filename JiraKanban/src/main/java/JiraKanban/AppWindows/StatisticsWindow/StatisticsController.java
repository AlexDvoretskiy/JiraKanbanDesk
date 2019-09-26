package JiraKanban.AppWindows.StatisticsWindow;

import JiraKanban.AppWindows.StatisticsWindow.Charts.AreaChartSeries;
import JiraKanban.AppWindows.StatisticsWindow.Charts.PieChartSlices;
import JiraKanban.JiraService.JiraService;
import JiraKanban.Utils.GUI.ScaledButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Класс-контроллер для окна статистики
 */
public class StatisticsController {

    @FXML
    Label quantityLabel;

    @FXML
    PieChart pieChart;

    @FXML
    AreaChart<String, Integer> areaChart;

    @FXML
    HBox buttonBox;

    /**
     * Инициализируем объекты окна статистики
     */
    @FXML
    public void initialize() {
        initCloseButton();

        AreaChartSeries areaChartSeries = new AreaChartSeries();
        PieChartSlices pieChartSlices = new PieChartSlices();

        final ObservableList<XYChart.Series<String, Integer>> seriesAreaChart = areaChartSeries.getData();
        final ObservableList<PieChart.Data> pieChartData = pieChartSlices.getData();

        quantityLabel.setText("Всего задач: " + JiraService.getIssueList().size());
        areaChart.setData(seriesAreaChart);
        pieChart.setData(pieChartData);
    }

    public void initCloseButton(){
        ScaledButton closeButton = new ScaledButton("images/ButtonsImg/cancelButtonImg.png", "Закрыть");
        closeButton.setText("Закрыть");

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StatisticsWindow.getStage().close();
            }
        });
        buttonBox.getChildren().add(closeButton);
    }
}

